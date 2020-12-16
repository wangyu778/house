package com.love.house.interceptor.handler;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wy
 * @Date: 2020/10/30 15:56
 * @Description 认证失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        response.setContentType("application/json;charset=utf-8");
        ServerResponse<ResponseCode> respBean;
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            respBean = ServerResponse.createByErrorMessage("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
            respBean = ServerResponse.createByErrorMessage("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean = ServerResponse.createByErrorMessage("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            respBean = ServerResponse.createByErrorMessage("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            respBean = ServerResponse.createByErrorMessage("账户被禁用，请联系管理员!");
        } else {
            respBean = ServerResponse.createByErrorMessage("登录失败!");
        }
//        new GalenWebMvcWrite().writeToWeb(response, respBean);
        response.sendRedirect("/index");
    }
}
