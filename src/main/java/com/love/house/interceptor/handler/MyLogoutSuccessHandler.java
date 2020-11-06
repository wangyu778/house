package com.love.house.interceptor.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    private static Logger LOG = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String ipAddr = ((WebAuthenticationDetails)(authentication.getDetails())).getRemoteAddress();
        String userName = ((UserDetails) authentication.getPrincipal()).getUsername();
        LOG.info("["+userName+"]-["+ipAddr+"]-[注销成功!]");
        response.sendRedirect("/login");
    }


}
