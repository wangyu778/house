package com.love.house.configure;

import com.love.house.utils.Md5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: wy
 * @Date: 2020/12/11 19:37
 * @Description: 自定义验证密码的加密方式
 */
public class Md5PassWordEncoder implements PasswordEncoder {

    private static Logger LOG = LoggerFactory.getLogger(Md5PassWordEncoder.class);

    @Override
    public String encode(CharSequence rawPassword) {
        return Md5.stringToMd51(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            return Md5.stringToMd51(rawPassword.toString()).equals(encodedPassword);
        } else {
            LOG.warn("Empty encoded password");
            return false;
        }
    }
}
