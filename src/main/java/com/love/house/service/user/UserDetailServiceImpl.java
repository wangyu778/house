package com.love.house.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2020/10/30 16:21
 * @Description: 实现了UserDetailsService接口中的loadUSerByUserName方法，方法执行成功后返回UserDetails对象，
 * 为构建Authentication对象提供必须的信息，UserDetails中包含了用户名、密码、角色等信息。
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
