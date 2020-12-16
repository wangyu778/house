package com.love.house.controller;

import com.love.house.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Author: wy
 * @Date: 2020/12/7 12:02
 * @Description: 登陆控制层
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }

}
