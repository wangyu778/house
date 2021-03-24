package com.love.house.controller.manage;

import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseFood;
import com.love.house.entity.HouseRoom;
import com.love.house.model.PageProperties;
import com.love.house.service.deliciousFood.DeliciousFoodService;
import com.love.house.service.manage.ManageService;
import com.love.house.service.renting.RentingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.*;

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
    @Resource
    private DeliciousFoodService foodService;

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

    @ApiOperation("返回-住房管理")
    @PostMapping(value = "/updateHouseWin")
    public ModelAndView updateHouseWin(@RequestParam Integer roomId){
        return new ModelAndView("manage/manage-update-house").addObject("houseRoom",manageService.getHouse(roomId));
    }

    @ApiOperation("返回-新增House-房屋列表")
    @PostMapping(value = "/deleteHouse")
    @ResponseBody
    public ServerResponse<String> deleteHouse(@RequestParam Integer roomId){
        return manageService.deleteHouse(roomId);
    }

    @ApiOperation("修改房屋")
    @PostMapping(value = "/updateHouse")
    @ResponseBody
    public ServerResponse<String> updateHouse(@RequestBody HouseRoom houseRoom){
        return manageService.updateHouse(houseRoom);
    }

    @ApiOperation("返回-美食管理-主界面")
    @PostMapping(value = "/foodManageIndex")
    public ModelAndView foodManageIndex(){
        return new ModelAndView("manage/manage-food-index");
    }

    @ApiOperation("返回-美食管理-美食列表")
    @PostMapping(value = "/getFoodList")
    public ModelAndView getFoodList(@RequestBody PageProperties properties){
        return new ModelAndView("manage/manage-food-list").addObject("properties", foodService.getFoodList(properties));
    }

    @ApiOperation("删除商家")
    @PostMapping(value = "/deleteFood")
    @ResponseBody
    public ServerResponse<String> deleteFood(@RequestParam Integer foodId){
        return manageService.deleteFood(foodId);
    }

    @ApiOperation("返回-新建商家页面")
    @PostMapping(value = "/newFoodWin")
    public ModelAndView newFoodWin(){
        return new ModelAndView("manage/manage-food-new");
    }

    @ApiOperation("新增美食商家")
    @PostMapping(value = "/newFood")
    @ResponseBody
    public ServerResponse<String> newFood(@RequestBody HouseFood houseFood){
        return manageService.newFood(houseFood);
    }

    @ApiOperation("返回-新建商家页面")
    @PostMapping(value = "/updateFoodWin")
    public ModelAndView updateFoodWin(@RequestParam Integer foodId){
        return new ModelAndView("manage/manage-food-update").addObject("houseFood",manageService.getFood(foodId));
    }

    @ApiOperation("删除商家")
    @PostMapping(value = "/deleteDiscount")
    @ResponseBody
    public ServerResponse<String> deleteDiscount(@RequestParam Integer id){
        return manageService.deleteDiscount(id);
    }

    @ApiOperation("返回-报修列表页面")
    @PostMapping(value = "/getRepairList")
    public ModelAndView getRepairList(){
        return new ModelAndView("manage/manage-house-repair-list").addObject("repairList",manageService.getRepairList());
    }

    @ApiOperation("修改报修信息")
    @PostMapping(value = "/updateRepair")
    @ResponseBody
    public ServerResponse<String> updateRepair(@RequestParam Integer id){
        return manageService.updateRepair(id);
    }

    @ApiOperation("申请看房")
    @PostMapping(value = "/applyHouse")
    @ResponseBody
    public ServerResponse<String> applyHouse(@RequestParam Integer roomId){
        return manageService.applyHouse(roomId);
    }

    @ApiOperation("返回-申请看房页面")
    @PostMapping(value = "/getApplyHouse")
    public ModelAndView getApplyHouse(){
        return new ModelAndView("manage/manage-house-apply-list").addObject("applyList",manageService.getApplyList());
    }

    @ApiOperation("修改看房信息")
    @PostMapping(value = "/updateApply")
    @ResponseBody
    public ServerResponse<String> updateApply(@RequestParam Integer id){
        return manageService.updateApply(id);
    }

    @ApiOperation("上传头像")
    @PostMapping(value = "/importHeadImg")
    @ResponseBody
    public ServerResponse<String> importHeadImg(@RequestParam("headImg") MultipartFile headImg, @RequestParam("roomNumber") String roomNumber) throws FileNotFoundException {
        return manageService.saveRoomImg(headImg, roomNumber);
    }

}
