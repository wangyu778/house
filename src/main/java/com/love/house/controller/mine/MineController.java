package com.love.house.controller.mine;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.User;
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

    @ApiOperation("返回-我的-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        return new ModelAndView("mine/index").addObject("user",baseService.getUser(baseService.getUserId()));
    }

    @ApiOperation("返回注册用户界面")
    @GetMapping(value = "/userRegister")
    public ModelAndView userRegister(){
        return new ModelAndView("mine/user-register");
    }

    @ApiOperation("保存新建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true)
    @PutMapping(value = "/saveUser")
    public ModelAndView saveUser(User user){
        ServerResponse<ResponseCode> responseCodeServerResponse = mineService.saveUser(user);
        if(responseCodeServerResponse.getStatus() == ResponseCode.SUCCESS.getCode()){
            return new ModelAndView("index").addObject("ServerResponse",responseCodeServerResponse);
        }else {
            return new ModelAndView("mine/user-register").addObject("ServerResponse",responseCodeServerResponse);
        }
    }

    @ApiOperation("返回租房详情界面")
    @GetMapping(value = "/rentalDetails")
    public ModelAndView getRentalDetails(){
        return new ModelAndView("mine/mine-rentalDetails").addObject("HouseRoom",mineService.getRentalDetails());
    }

    @ApiOperation("返回报修详情界面")
    @GetMapping(value = "/repairInfo")
    public ModelAndView getRepairInfo(){
        return new ModelAndView("mine/mine-repairInfo");
    }

    @ApiOperation("返回美食定单界面")
    @GetMapping(value = "/foodOrder")
    public ModelAndView getFoodOrder(){
        return new ModelAndView("mine/mine-foodOrder");
    }

    @ApiOperation("返回我的收藏界面")
    @GetMapping(value = "/collection")
    public ModelAndView getCollection(){
        return new ModelAndView("mine/mine-collection").addObject("collectionList",mineService.getCollectionList());
    }

}
