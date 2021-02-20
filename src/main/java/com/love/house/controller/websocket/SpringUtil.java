package com.love.house.controller.websocket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: wy
 * @Date: 2021/2/19 11:19
 * @Description: 根据容器获取对象的工具类
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext(){

        return applicationContext;

    }


    public static Object getBean(String beanName){

        return applicationContext.getBean(beanName);

    }


    public static <T> T getBean(Class<T> clazz){

        return (T)applicationContext.getBean(clazz);

    }


}
