package com.love.house.model.websocket;

import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:19
 * @Description: 用户认证成功, 会广播用户加入群聊的通知 Message
 */
public class UserJoinNoticeRequest implements Message{

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
