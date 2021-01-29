package com.love.house;

import com.love.house.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: wy
 * @Date: 2021/1/25 11:29
 * @Description: redis测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    /**
     * 继承了StringRedisTemplate 使用的的是StringRedisSerializer字符串序列化的方式
     * 字符串和二进制数组的直接转换
     *      string.getBytes(charset []
     *      new String(bytes,charset[])
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void stringTestRedisSetKey() {
        stringRedisTemplate.opsForValue().set("hello", "world");
    }


    /**
     * jdk字符串序列化方式，一般不用，因为在setKey 和 getKey的时候内容前面都会有奇怪的16进制的字符串
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void jdkTestRedisSetKey() {
        redisTemplate.opsForValue().set("hello", "world");
    }


    /**
     * Json厂家方式，会有一个@class 但是Json字符串占用变大，实际项目中，也一般不用
     */
    @Test
    public void jsonTestRedisSetKey() {
        User user = new User();
        user.setUserId("3");
        user.setUserName("张三");
        user.setSex(2);
        String key = "user:".concat(user.getUserId());
        redisTemplate.opsForValue().set(key,user);

        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }

    @Test
    public void testStringGetKeyUserCache() {
        String key = "user:".concat("3");
        Object value = redisTemplate.opsForValue().get(key);
        System.out.println(value);
    }
     //还有一种基于xml序列化的方式，就是将string和二进制数组转换，无用

}
