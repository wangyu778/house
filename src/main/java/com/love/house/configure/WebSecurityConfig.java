package com.love.house.configure;

import com.love.house.interceptor.MyAccessDecisionManager;
import com.love.house.interceptor.MyFilterInvocationSecurityMetadataSource;
import com.love.house.interceptor.handler.MyAccessDeniedHandler;
import com.love.house.interceptor.handler.MyAuthenticationFailureHandler;
import com.love.house.interceptor.handler.MyAuthenticationSuccessHandler;
import com.love.house.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: wy
 * @Date: 2020/10/30 15:47
 * @Description: spring-security权限管理的核心配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 实现了UserDetailService接口
     */
    @Autowired
    private UserDetailServiceImpl userDetailService;

    /**
     * 权限过滤器
     */
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    /**
     * 权限决策器
     */
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    /**
     * 自定义错误（403）返回数据
     */
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    /**
     * 认证成功处理
     */
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 认证失败处理
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 配日志userDetails的数据源，密码加密格式
     * @param auth [auth]
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
