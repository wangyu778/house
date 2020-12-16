package com.love.house.controller.mine;

import com.love.house.common.ResponseCode;
import com.love.house.common.ServerResponse;
import com.love.house.entity.User;
import com.love.house.service.baseService.BaseService;
import com.love.house.service.mine.MineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Resource
    private MineService mineService;

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        return new ModelAndView("mine/index").addObject("user",baseService.getUser(baseService.getUserId()));
    }

    @RequestMapping(value = "/userRegister",method = RequestMethod.GET)
    public ModelAndView userRegister(){
        return new ModelAndView("mine/user-register");
    }

    @RequestMapping(value = "/saveUser")
    public ModelAndView saveUser(User user){
        ServerResponse<ResponseCode> responseCodeServerResponse = mineService.saveUser(user);
        if(responseCodeServerResponse.getStatus() == ResponseCode.SUCCESS.getCode()){
            return new ModelAndView("index").addObject("ServerResponse",responseCodeServerResponse);
        }else {
            return new ModelAndView("mine/user-register").addObject("ServerResponse",responseCodeServerResponse);
        }
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public ModelAndView getUserInfo(){
        return new ModelAndView("mine/mine-userInfo");
    }

}
