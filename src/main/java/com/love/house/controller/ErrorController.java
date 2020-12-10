package com.love.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wy
 * @Date: 2020/12/10 14:14
 * @Description: 错误页面
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public ModelAndView error404(){
        return new ModelAndView("error/error404");
    }

 }
