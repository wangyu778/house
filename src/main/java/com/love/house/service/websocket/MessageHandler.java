package com.love.house.service.websocket;

import com.love.house.model.websocket.Message;
import org.springframework.stereotype.Service;

import javax.websocket.Session;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:29
 * @Description: websocket消息处理器接口
 */
@Service
public interface MessageHandler <T extends Message> {

    /**
     * 执行处理消息
     *
     * @param session 会话
     * @param message 消息
     */
    void execute(Session session, T message);

    /**
     * 获取消息类型
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();

}
