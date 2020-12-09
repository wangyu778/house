package com.love.house.controller.overview;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wy
 * @Date: 2020/12/7 12:04
 * @Description: 首页概述控制层
 */
@RestController
@RequestMapping("/overview")
public class OverViewController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("overview/overview-index");
    }
}
