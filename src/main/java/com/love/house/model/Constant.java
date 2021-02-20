package com.love.house.model;

import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2020/12/4 14:13
 * @Description: 公共常量类
 */
@Component("Constant")
public class Constant {

    public static final String SESSION_USERID = "UserId";
    public static final String SESSION_LOGINIP = "LoginIp";
    public static final String SESSION_LOGINDATE = "LoginDate";

    /**
     * 聊天室，当前在线人数
     */
    public static int onlineCount = 0;

}
