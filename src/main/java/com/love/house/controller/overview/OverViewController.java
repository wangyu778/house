package com.love.house.controller.overview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: wy
 * @Date: 2020/12/7 12:04
 * @Description: 首页概述控制层
 */
@Controller
@RequestMapping("/overview")
public class OverViewController {

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    public String index(){
        return "overview/overview-index";
    }


}
