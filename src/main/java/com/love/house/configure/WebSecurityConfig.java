package com.love.house.configure;

import com.love.house.interceptor.MyAccessDecisionManager;
import com.love.house.interceptor.MyFilterInvocationSecurityMetadataSource;
import com.love.house.interceptor.handler.MyAccessDeniedHandler;
import com.love.house.interceptor.handler.MyAuthenticationFailureHandler;
import com.love.house.interceptor.handler.MyAuthenticationSuccessHandler;
import com.love.house.interceptor.handler.MyLogoutSuccessHandler;
import com.love.house.service.security.user.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private DataSource dataSource;

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

    public static String[] FreeUrl = new String[]{
          "/login.html",
          "/ws",
          "/swagger-ui.html",
          "/v2/**",
          "/swagger-resources/**",
          "/login",
          "/index",
          "/index.html",
          "/static/**",
          "/images/**",
          "/images/**",
          "/js/**",
          "/webjars/**",
          "/v2/api-docs",
          "/configuration/ui",
          "/configuration/security",

          //overview
          "/overview/index",

          //mine
          "/mine/index",
          "/mine/userRegister",
          "/mine/saveUser",
          "/mine/getUserInfo",
          "/mine/rentalDetails",
          "/mine/repairInfo",
          "/mine/foodOrder",
          "/mine/collection",
          "/mine/applyRepairWin",
          "/mine/newRepair",

            //forum
          "/forum/index",


            //deliciousFood
            "/deliciousFood/index",
            "/deliciousFood/getFoodList",
            "/deliciousFood/collectionFood",


            //renting
            "/renting/index",
            "/renting/getHouseList",

            //manage
            "/manage/houseManageIndex",
            "/manage/newHouse",
            "/manage/newHouseWin",
            "/manage/forumManageIndex",
            "/manage/updateHouseWin",
            "/manage/deleteHouse",
            "/manage/updateHouse",
            "/manage/foodManageIndex",
            "/manage/getFoodList",
            "/manage/deleteFood",
            "/manage/updateFoodWin",
            "/manage/updateFood",
            "/manage/newFoodWin",
            "/manage/newFood",
            "/manage/deleteDiscount",
            "/manage/getRepairList",
            "/manage/updateRepair",
            "/manage/applyHouse",
            "/manage/updateApply",
            "/manage/getApplyHouse",
            "/manage/importHeadImg",


    };



    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new Md5PassWordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    /**
     * 配日志userDetails的数据源，密码加密格式
     * @param auth [auth]
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 配置放行资源
     * @param web 【web】
     * @throws Exception 异常
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(FreeUrl);
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
        http
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .and()
                /*
                 * loginPage: 用户未登陆时，访问任何资源都要跳到该路径，即登陆页面
                 * loginProcessingUrl：登陆表单form中的action地址，也就是处理认证请求的路径
                 */
                .formLogin()
                    .loginPage("/index")
                    .loginProcessingUrl("/user/login")
                    /*
                     * 登陆表单form中用户名输入框input的name名，不修改的话默认是username
                     * 登陆表单form中密码输入框input的name名，不修改的话默认是password
                     */
                    .usernameParameter("username").passwordParameter("password")
                    //登陆失败后执行
                    .failureHandler(new MyAuthenticationFailureHandler())
                    //登陆成功后执行
                    .successHandler(new MyAuthenticationSuccessHandler())
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessHandler(new MyLogoutSuccessHandler())
                    .permitAll()
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
    }
}
