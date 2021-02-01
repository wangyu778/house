package com.love.house.controller.forum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wy
 * @Date: 2021/2/1 16:41
 * @Description: 论坛表现层
 */
@Controller
@Api(tags = "论坛相关接口")
@RequestMapping("/forum")
public class ForumController {

    @ApiOperation("返回-我的-主界面")
    @PostMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("forum/forum-index");
    }

}
