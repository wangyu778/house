package com.love.house.controller.deliciousFood;

import com.love.house.common.ServerResponse;
import com.love.house.model.PageProperties;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.deliciousFood.DeliciousFoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/2/23 16:43
 * @Description:
 */
@Controller
@Api(tags = "周边美食")
@RequestMapping("/deliciousFood")
public class DeliciousFoodController {

    @Resource
    private BaseService baseService;

    @Resource
    private DeliciousFoodService deliciousFoodService;

    @ApiOperation("返回-周边美食-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("deliciousFood/index");
    }

    @ApiOperation("返回美食列表")
    @PostMapping(value = "/getFoodList")
    public ModelAndView getFoodList(@RequestBody PageProperties properties){
        return new ModelAndView("deliciousFood/deliciousFood-food-list").addObject("properties",deliciousFoodService.getFoodList(properties));
    }

    @ApiOperation("收藏美食")
    @PostMapping(value = "/collectionFood")
    @ResponseBody
    public ServerResponse<String> collectionFood(@RequestParam("foodId") int foodId){
        if(null == baseService.getUserId()){
            return ServerResponse.createByErrorMessage("请先登陆");
        }
        return deliciousFoodService.collectionFood(foodId);
    }
}
