package com.love.house.mapper.redisDao;

import com.love.house.entity.User;
import com.love.house.utils.JSONUtil;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/1/25 15:03
 * @Description: user表对应的redis对象，用于反序列化
 */
@Repository
public class UserCacheDao {

    /**
     * 用户编号,生命key的前缀，并用冒号作为间隔隔开
     */
    private static final String KEY_PATTERN = "user:%d";

    /**
     * 通过 @Resource 注入指定名字的 RedisTemplate 对应的 Operations 对象，这样明确每个 KEY 的类型。
     */
    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations;

    /**
     * 声明 KEY_PATTERN 对应的 KEY 拼接方法，避免散落在每个方法中。
     */
    private static String buildKey(Integer id) {
        return String.format(KEY_PATTERN, id);
    }

    /**
     * 再往下就是各种方法封装的对应的操作了
     */
    public User get(Integer id) {
        String key = buildKey(id);
        String value = operations.get(key);
        return JSONUtil.parseObject(value, User.class);
    }

    public void set(Integer id, User object) {
        String key = buildKey(id);
        String value = JSONUtil.toJSONString(object);
        operations.set(key, value);
    }
}
