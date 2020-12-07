package com.love.house.service.security.resourse.token;

import com.love.house.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wy
 * @date 2020/10/26
 */
public interface TokenService {
    /**
     * 生成token
     * @return token
     */
    ServerResponse createToken();

    /**
     * 判断token是否存在
     * @param request 请求
     */
    void checkToken(HttpServletRequest request);
}
