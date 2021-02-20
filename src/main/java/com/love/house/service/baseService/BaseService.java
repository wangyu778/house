package com.love.house.service.baseService;

import com.love.house.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2020/12/4 14:06
 * @Description: 获取通用的数据库数据
 */
@Service
public interface BaseService {

    /**
     * 获取用户Id
     * @return userId
     */
    public String getUserId();

    /**
     * 获取用户信息
     * @param userId userId
     * @return User
     */
    public User getUser(String userId);

}
