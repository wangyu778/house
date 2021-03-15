package com.love.house.controller.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.love.house.entity.elasticsearchDo.ESMessDO;
import com.love.house.entity.elasticsearchDo.ESProductDO;
import com.love.house.mapper.elasticsearchDao.ProductRepository;
import com.love.house.mapper.elasticsearchDao.ProductRepository02;
import com.love.house.mapper.elasticsearchDao.ProductRepository03;
import com.love.house.model.Constant;
import com.love.house.model.websocket.AuthRequest;
import com.love.house.model.websocket.Message;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.websocket.MessageHandler;
import com.love.house.utils.WebSocketUtil;
import com.love.house.utils.random.RandomUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.Inet4Address;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: wy
 * @Date: 2021/1/29 14:36
 * @Description: 定义webSocket服务的端点
 */
@Controller
@ServerEndpoint("/ws")
public class WebSocketServerEndpoint implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 消息类型与 MessageHandler 的映射
     *
     * 注意，这里设置成静态变量。虽然说 WebsocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebsocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductRepository productRepository = SpringUtil.getBean(ProductRepository.class);
    @Autowired
    private ProductRepository02 productRepository2 = SpringUtil.getBean(ProductRepository02.class);
    @Autowired
    private ProductRepository03 productRepository3 = SpringUtil.getBean(ProductRepository03.class);

    @Override
    public void afterPropertiesSet(){
        // 通过 ApplicationContext 获得所有 MessageHandler Bean
        Collection<MessageHandler> values = applicationContext.getBeansOfType(MessageHandler.class).values();
        // 获得所有 MessageHandler Bean
        applicationContext.getBeansOfType(MessageHandler.class).values()
                // 添加到 handlers 中
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(), messageHandler));
        logger.info("[afterPropertiesSet][消息处理器数量：{}]", HANDLERS.size());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        logger.info("[onOpen][session({}) 接入]", session);
        addOnlineCount();
        // 1、解析 accessToken
        List<String> accessTokenValues = session.getRequestParameterMap().get("accessToken");
        String accessToken = !CollectionUtils.isEmpty(accessTokenValues) ? accessTokenValues.get(0) : null;
        // 2、创建 AuthRequest 消息类型
        AuthRequest authRequest = new AuthRequest();
        authRequest.setAccessToken(accessToken);
        // 3、获得消息处理器
        MessageHandler<AuthRequest> messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if (messageHandler == null) {
            logger.error("[onOpen][认证消息类型，不存在消息处理器]");
            return;
        }
        messageHandler.execute(session, authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 生产环境下，请设置成 debug 级别
        logger.info("[onOpen][session({}) 接收到一条消息({})]", session, message);
        try {
            // 获得消息类型
            JSONObject jsonMessage = new JSONObject();
            jsonMessage.put("type","SEND_TO_ALL_REQUEST");
            JSONObject messageJson = new JSONObject(2);
            // 1、解析 accessToken
            List<String> accessTokenValues = session.getRequestParameterMap().get("accessToken");
            String accessToken = !CollectionUtils.isEmpty(accessTokenValues) ? accessTokenValues.get(0) : null;
            messageJson.put("msgId", accessToken);
            messageJson.put("content", message);
            jsonMessage.put("body", messageJson.toString());
            String messageType = jsonMessage.getString("type");
            // 获得消息处理器
            MessageHandler messageHandler = HANDLERS.get(messageType);
            if (messageHandler == null) {
                logger.error("[onMessage][消息类型({}) 不存在消息处理器]", messageType);
                return;
            }
            // 解析消息
            Class<? extends Message> messageClass = this.getMessageClass(messageHandler);
            // 处理消息
            Message messageObj = JSON.parseObject(jsonMessage.getString("body"), messageClass);
            //将消息存入es中
//            ESMessDO esMessDO = new ESMessDO();
//            esMessDO.setId(Integer.parseInt(RandomUtil.generateDigitalStr(8)));
//            esMessDO.setMessage(message);
//            esMessDO.setSpeakTime(Long.toString(System.currentTimeMillis()));
//            esMessDO.setName(accessToken);
//            productRepository.save(esMessDO);
            messageHandler.execute(session, messageObj);
        } catch (Throwable throwable) {
            logger.info("[onMessage][session({}) message({}) 发生异常]", session, throwable);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
        subOnlineCount();
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    private Class<? extends Message> getMessageClass(MessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        // 此处，是以父类的接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) {
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }


    public static synchronized int getOnlineCount() {
        return Constant.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Constant.onlineCount = WebSocketServerEndpoint.getOnlineCount();
        Constant.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Constant.onlineCount = WebSocketServerEndpoint.getOnlineCount();
        Constant.onlineCount--;
    }

}
