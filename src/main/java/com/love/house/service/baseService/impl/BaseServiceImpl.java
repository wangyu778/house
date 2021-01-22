package com.love.house.service.baseService.impl;

import com.love.house.entity.User;
import com.love.house.mapper.HouseRoomMapper;
import com.love.house.mapper.UserMapping;
import com.love.house.model.Constant;
import com.love.house.service.baseService.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2020/12/4 14:08
 * @Description: BaseServiceImpl
 */
@Service("baseService")
public class BaseServiceImpl implements BaseService {

    @Resource
    private UserMapping userMapping;

    @Override
    public String getUserId() {
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (String) session.getAttribute(Constant.SESSION_USERID);
    }

    @Override
    public User getUser(String userId) {
        return userMapping.getUser(userId);
    }

}
