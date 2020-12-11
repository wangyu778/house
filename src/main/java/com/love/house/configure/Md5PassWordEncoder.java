package com.love.house.configure;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: wy
 * @Date: 2020/12/11 19:37
 * @Description: MD5加密
 */
public class Md5PassWordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }

}
