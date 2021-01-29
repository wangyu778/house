package com.love.house.configure.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @Author: wy
 * @Date: 2021/1/25 14:46
 * @Description: 自定义redisSerializer序列化
 */
public class GenericFastJsonRedisSerializer implements RedisSerializer<Object> {

    private final static ParserConfig DEFAULT_REDIS_CONFIG = new ParserConfig();
    static { DEFAULT_REDIS_CONFIG.setAutoTypeSupport(true);}

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        // 空，直接返回空数组
        if (object == null) {
            return new byte[0];
        }
        try {
            // 使用 JSON 进行序列化成二进制数组，同时通过 SerializerFeature.WriteClassName 参数，声明写入类全名。
            return JSON.toJSONBytes(object, SerializerFeature.WriteClassName);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        // 如果为空，则返回空对象
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            // 使用 JSON 解析成对象。
            return JSON.parseObject(new String(bytes, IOUtils.UTF8), Object.class, DEFAULT_REDIS_CONFIG);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }
}
