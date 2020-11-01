package com.love.house.service.user;

import com.love.house.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2020/11/1 10:24
 * @Description: 查询用户的详情信息
 */
@Service
public interface UserCacheService {

    public User selectByUserName(String userName);

}
