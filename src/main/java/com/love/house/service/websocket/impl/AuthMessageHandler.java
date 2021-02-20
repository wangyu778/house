package com.love.house.service.websocket.impl;

import com.love.house.model.Constant;
import com.love.house.model.websocket.AuthRequest;
import com.love.house.model.websocket.AuthResponse;
import com.love.house.model.websocket.UserJoinNoticeRequest;
import com.love.house.service.websocket.MessageHandler;

import com.love.house.utils.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.Session;
/**
 * @Author: wy
 * @Date: 2021/1/29 16:32
 * @Description:
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {

    @Override
    public void execute(Session session, AuthRequest message) {
        // 如果未传递 accessToken
        if (StringUtils.isEmpty(message.getAccessToken())) {
            AuthResponse authResponseSuccess = new AuthResponse();
            authResponseSuccess.setCode(1);
            authResponseSuccess.setMessage("认证 accessToken 未传入");
            WebSocketUtil.send(session, AuthResponse.TYPE, authResponseSuccess);
            return;
        }

        // 添加到 WebSocketUtil 中
        // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        WebSocketUtil.addSession(session, message.getAccessToken());

        // 判断是否认证成功。这里，假装直接成功
//        AuthResponse authResponseFail = new AuthResponse();
//        authResponseFail.setCode(0);
//        WebSocketUtil.send(session, AuthResponse.TYPE, authResponseFail);

        // 通知所有人，某个人加入了。这个是可选逻辑，仅仅是为了演示
        // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        UserJoinNoticeRequest userJoinNoticeRequest = new UserJoinNoticeRequest();
        userJoinNoticeRequest.setNickname(message.getAccessToken());
        userJoinNoticeRequest.setOnlineCount(Constant.onlineCount);
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE, userJoinNoticeRequest);
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

}
