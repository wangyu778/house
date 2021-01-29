package com.love.house.model.websocket;

import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:24
 * @Description: 发送给所有人的群聊消息
 */
@Component
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

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
