package com.love.house.configure.redis;

import com.love.house.configure.redis.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author: wy
 * @Date: 2021/1/25 14:31
 * @Description: redis配置类
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
       // 创建 RedisTemplate 对象
       RedisTemplate<String, Object> template = new RedisTemplate<>();
       // 设置 RedisConnection 工厂。😈 它就是实现多种 Java Redis 客户端接入的秘密工厂。
       template.setConnectionFactory(factory);
       // 使用 String 序列化方式，序列化 KEY 。
       template.setKeySerializer(RedisSerializer.string());
       // 使用 JSON 序列化方式（由于JSON字符串占用太大，这里自定义的序列化方式 ），序列化 VALUE 。
       template.setValueSerializer(new GenericFastJsonRedisSerializer());
       return template;
   }

}
