package com.love.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wy
 * @Date: 2020/12/7 12:02
 * @Description: 首页控制层
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
