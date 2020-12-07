package com.love.house.service.security.resourse;

import com.love.house.service.baseService.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wy
 * @Date: 2020/11/1 19:47
 * @Description: 资源权限类
 */
@Service("resourceService")
public class ResourceService {

    /**
     * spring4.0之后不在推荐使用AutoWired这种基于字段的注入
     */
    private final BaseService baseService;

    @Autowired
    public ResourceService(BaseService baseService){
        this.baseService = baseService;
    }

    /**
     * 判断用户是否有该模块的权限
     * @param url 模块路径
     * @return 结果
     */
    public boolean isRl(String url){
        return isAuthority(baseService.getUserId(),url);
    }

    /**
     * 判断用户是否有指定url的权限
     * @param userId 用户Id
     * @param url 路径
     * @return 结果
     */
    public boolean isAuthority(String userId, String url){
        return false;
    }
}
