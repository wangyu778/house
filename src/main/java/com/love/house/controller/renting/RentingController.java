package com.love.house.controller.renting;

import com.love.house.model.PageProperties;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.renting.RentingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/3/4 16:38
 * @Description: 租房控制层
 */
@Controller
@Api(tags = "租房")
@RequestMapping("/renting")
public class RentingController {

    @Resource
    private BaseService baseService;
    @Resource
    private RentingService rentingService;

    @ApiOperation("返回-租房-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        return new ModelAndView("renting/index");
    }

    @ApiOperation("返回-租房-房屋列表")
    @PostMapping(value = "/getHouseList")
    public ModelAndView getHouseList(@RequestBody PageProperties properties){
        return new ModelAndView("renting/renting-house-list").addObject("properties", rentingService.getHouseList(properties));
    }
}
