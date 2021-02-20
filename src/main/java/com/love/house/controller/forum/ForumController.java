package com.love.house.controller.forum;

import com.love.house.model.Constant;
import com.love.house.service.baseService.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author: wy
 * @Date: 2021/2/1 16:41
 * @Description: 论坛表现层
 */
@Controller
@Api(tags = "论坛相关接口")
@RequestMapping("/forum")
public class ForumController {

    @Resource
    private BaseService baseService;

    @ApiOperation("返回-论坛-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        if(null == baseService.getUserId()){
            return new ModelAndView("login");
        }
        return new ModelAndView("forum/forum-index").addObject("onlineCount", Constant.onlineCount);
    }

}
