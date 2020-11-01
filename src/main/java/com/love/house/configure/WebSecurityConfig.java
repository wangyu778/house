package com.love.house.configure;

import com.love.house.interceptor.MyAccessDecisionManager;
import com.love.house.interceptor.MyFilterInvocationSecurityMetadataSource;
import com.love.house.interceptor.handler.MyAccessDeniedHandler;
import com.love.house.interceptor.handler.MyAuthenticationFailureHandler;
import com.love.house.interceptor.handler.MyAuthenticationSuccessHandler;
import com.love.house.interceptor.handler.MyLogoutSuccessHandler;
import com.love.house.service.user.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

    /**
     * 配置放行资源
     * @param web 【web】
     * @throws Exception 异常
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login_p", "/favicon.ico")
                // 给 swagger 放行；不需要权限能访问的资源
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security");

        super.configure(web);
    }

    /**
     * @Description: HttpSecurity包含了原数据（主要是url）
    *  通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
    *  此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
    *  如果不匹配，返回 MyAccessDeniedHandler
     * @param http [http]
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource((FilterInvocationSecurityMetadataSource) myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager((AccessDecisionManager) myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .failureHandler((AuthenticationFailureHandler) myAuthenticationFailureHandler)
                .successHandler((AuthenticationSuccessHandler) myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler((AccessDeniedHandler) myAccessDeniedHandler);
    }
}
