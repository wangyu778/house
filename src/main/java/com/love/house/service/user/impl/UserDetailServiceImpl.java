package com.love.house.service.user.impl;

import com.love.house.entity.User;
import com.love.house.mapper.UserMapper;
import com.love.house.service.user.UserCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wy
 * @Date: 2020/10/30 16:21
 * @Description: 实现了UserDetailsService接口中的loadUSerByUserName方法，方法执行成功后返回UserDetails对象，
 * 为构建Authentication对象提供必须的信息，UserDetails中包含了用户名、密码、角色等信息。
 */
@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private static Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userCacheService.selectByUserName(s);
            if(user == null){
                throw new UsernameNotFoundException("用户名不存在");
            }
        }catch (Exception e){
            LOG.debug("get user: id = "+s);
        }
        return user;
    }
}
