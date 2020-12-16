package com.love.house.interceptor.handler;

import com.love.house.model.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wy
 * @Date: 2020/10/30 15:57
 * @Description 认证成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static Logger LOG = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String ipAddr = ((WebAuthenticationDetails)(authentication.getDetails())).getRemoteAddress();
        String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
        LOG.info("[login]-["+userId+"]-["+ipAddr+"]-[登录成功]");
        HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_USERID,userId);
        session.setAttribute(Constant.SESSION_LOGINIP,ipAddr);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        session.setAttribute(Constant.SESSION_LOGINDATE,dateFormat.format(new Date()));
        response.sendRedirect("/index");
    }
}
