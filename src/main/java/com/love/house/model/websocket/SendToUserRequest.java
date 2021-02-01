package com.love.house.model.websocket;

import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:26
 * @Description: 在服务端接收到发送消息的请求，需要转发消息给对应的人,发送消息给一个用户的 Message。
 */
public class SendToUserRequest implements Message {

    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
