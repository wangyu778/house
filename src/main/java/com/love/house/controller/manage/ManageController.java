package com.love.house.controller.manage;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseRoom;
import com.love.house.model.PageProperties;
import com.love.house.service.manage.ManageService;
import com.love.house.service.renting.RentingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/3/11 17:23
 * @Description: 公寓管理控制层
 */
@Controller
@Api(tags = "公寓管理")
@RequestMapping("/manage")
public class ManageController {

    @Resource
    private RentingService rentingService;
    @Resource
    private ManageService manageService;

    @ApiOperation("返回-公寓管理-主界面")
    @PostMapping(value = "/houseManageIndex")
    public ModelAndView index(){
        return new ModelAndView("manage/houseManage-index");
    }

    @ApiOperation("返回-聊天室监测-主界面")
    @PostMapping(value = "/forumManageIndex")
    public ModelAndView forumManageIndex(){
        return new ModelAndView("manage/manage-forum-index");
    }

    @ApiOperation("返回-公寓管理-房屋列表")
    @PostMapping(value = "/getHouseList")
    public ModelAndView getHouseList(@RequestBody PageProperties properties){
        return new ModelAndView("manage/manage-house-list").addObject("properties", rentingService.getHouseList(properties));
    }


    @ApiOperation("返回-公寓管理-房屋列表")
    @GetMapping(value = "/newHouseWin")
    public ModelAndView newHouseWin(){
        return new ModelAndView("manage/manage-newHouse");
    }

    @ApiOperation("返回-新增House-房屋列表")
    @PostMapping(value = "/newHouse")
    @ResponseBody
    public ServerResponse<String> newHouse(@RequestBody HouseRoom houseRoom){
        return manageService.newHouse(houseRoom);
    }
}
