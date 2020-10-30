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
@RequestMapping("/index")
public class TestController {

    @RequestMapping("/login")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
