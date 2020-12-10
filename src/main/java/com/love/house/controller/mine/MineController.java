package com.love.house.controller.mine;

import com.love.house.service.baseService.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2020/12/9 20:12
 * @Description: 我的表现层
 */
@Controller
@RequestMapping("/mine")
public class MineController {

    @Resource
    private BaseService baseService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        return new ModelAndView("mine/index");
    }

}
