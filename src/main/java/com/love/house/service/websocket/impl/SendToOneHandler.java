package com.love.house.service.websocket.impl;

import com.love.house.model.websocket.SendResponse;
import com.love.house.model.websocket.SendToOneRequest;
import com.love.house.model.websocket.SendToUserRequest;
import com.love.house.service.websocket.MessageHandler;
import com.love.house.utils.WebSocketUtil;

import javax.websocket.Session;

/**
 * @Author: wy
 * @Date: 2021/1/29 16:45
 * @Description:
 */
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {

    @Override
    public void execute(Session session, SendToOneRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse();
        sendResponse.setMsgId(message.getMsgId());
        sendResponse.setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest();
        sendToUserRequest.setMsgId(message.getMsgId());
        sendToUserRequest.setContent(message.getContent());
        // 广播发送
        WebSocketUtil.send(message.getToUser(), SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }

}
