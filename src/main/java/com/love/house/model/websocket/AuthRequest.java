package com.love.house.model.websocket;

import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:13
 * @Description: 用户认证请求 (认证token ,在webSocket协议中，我们也需要认证当前连接，用户身份是什么，
 *  一般情况下，我们采用用户调用http登陆接口，登陆成功后放回访问令牌accessToken)
 */
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证 Token
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
