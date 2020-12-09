package com.love.house.interceptor;

import com.love.house.entity.Role;
import com.love.house.entity.SecuritySysPermission;
import com.love.house.mapper.UserMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wy
 * @Date: 2020/10/30 16:00
 * @Description: 权限资源过滤器
 *  Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 *  自定义权限资源过滤器，实现动态的权限验证
 *  它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final Logger log = LoggerFactory.getLogger(MyFilterInvocationSecurityMetadataSource.class);

    @Autowired
    private UserMapping userMapping;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 返回本次访问需要的权限，可以有多少权限
     * @param o [o]
     * @return  ConfigAttribute
     * @throws IllegalArgumentException 方法的参数异常
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //去数据库查询资源
        Map<String,Object> filterMap = new HashMap<>(2);
        filterMap.put("enabled",1);
        List<SecuritySysPermission> permissionList = userMapping.getPermissionList(filterMap);
        for (SecuritySysPermission permission : permissionList) {
            if (antPathMatcher.match(permission.getUrl(), requestUrl)
                    && permission.getRoles().size() > 0) {
                List<Role> roles = permission.getRoles();
                int size = roles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getRoleName();
                }
                log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, values);
                return SecurityConfig.createList(values);
            }
        }

        /*
         * 如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
         * 此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
         */
        log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, "ROLE_LOGIN");
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * @Description: 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
     * @return ConfigAttribute
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * @Description: 方法返回类对象是否支持校验，
     * web项目一般使用FilterInvocation来判断，或者直接返回true
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
