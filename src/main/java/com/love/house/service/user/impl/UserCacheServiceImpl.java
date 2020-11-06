package com.love.house.service.user.impl;

import com.love.house.entity.User;
import com.love.house.service.user.UserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: wy
 * @Date: 2020/11/1 10:25
 */
public class UserCacheServiceImpl implements UserCacheService {

    private static Logger LOG = LoggerFactory.getLogger(UserCacheServiceImpl.class);

    @Override
    public User selectByUserName(String userName) {
        return null;
    }
}
