package com.love.house.controller.mine;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.HouseFood;
import com.love.house.entity.HouseRepair;
import com.love.house.entity.User;
import com.love.house.mapper.mysqlMapper.HouseApplyUserMapper;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.mine.MineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2020/12/9 20:12
 * @Description: 我的表现层
 */
@Controller
@Api(tags = "我的相关接口")
@RequestMapping("/mine")
public class MineController {

    @Resource
    private BaseService baseService;
    @Resource
    private MineService mineService;
    @Resource
    private HouseApplyUserMapper applyUserMapper;

    @ApiOperation("返回-我的-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        User user = baseService.getUser(baseService.getUserId());
        user.setApplyUser(applyUserMapper.selectByPrimaryKey(user.getUserId()));
        return new ModelAndView("mine/index").addObject("user",user);
    }

    @ApiOperation("返回注册用户界面")
    @GetMapping(value = "/userRegister")
    public ModelAndView userRegister(){
        return new ModelAndView("mine/user-register");
    }

    @ApiOperation("保存新建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true)
    @PostMapping(value = "/saveUser")
    @ResponseBody
    public ServerResponse<String> saveUser(@RequestBody User user){
        return mineService.saveUser(user);
    }

    @ApiOperation("返回租房详情界面")
    @GetMapping(value = "/rentalDetails")
    public ModelAndView getRentalDetails(){
        return new ModelAndView("mine/mine-rentalDetails").addObject("houseRoom",mineService.getRentalDetails());
    }

    @ApiOperation("返回报修详情界面")
    @GetMapping(value = "/repairInfo")
    public ModelAndView getRepairInfo(){
        return new ModelAndView("mine/mine-repairInfo").addObject("repairList",mineService.getRepairList());
    }

    @ApiOperation("返回美食定单界面")
    @GetMapping(value = "/foodOrder")
    public ModelAndView getFoodOrder(){
        return new ModelAndView("mine/mine-foodOrder");
    }

    @ApiOperation("返回新建报修任务界面")
    @PostMapping(value = "/applyRepairWin")
    public ModelAndView applyRepairWin(){
        return new ModelAndView("mine/mine-repair-new");
    }

    @ApiOperation("返回我的收藏界面")
    @GetMapping(value = "/collection")
    public ModelAndView getCollection(){
        return new ModelAndView("mine/mine-collection").addObject("collectionList",mineService.getCollectionList());
    }


    @ApiOperation("新增报修信息")
    @PostMapping(value = "/newRepair")
    @ResponseBody
    public ServerResponse<String> newRepair(@RequestBody HouseRepair houseRepair){
        return mineService.newRepair(houseRepair);
    }

}
