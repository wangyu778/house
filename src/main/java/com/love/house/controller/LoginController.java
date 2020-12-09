package com.love.house.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: wy
 * @Date: 2020/12/7 12:02
 * @Description: 登陆控制层
 */
@RestController
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login");
    }

}
