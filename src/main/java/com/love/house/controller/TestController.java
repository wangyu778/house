package com.love.house.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * 测试表现层
 * @author wy
 * @date 2020/10/26
 */
@RestController
public class TestController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("/login");
    }

    @RequestMapping("/favicon.ico")
    public ModelAndView favicon(){
        return new ModelAndView("favicon.ico");
    }

}
