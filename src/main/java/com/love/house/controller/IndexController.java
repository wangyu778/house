package com.love.house.controller;

import com.love.house.common.ServerResponse;
import com.love.house.model.Constant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @Author: wy
 * @Date: 2020/12/7 12:02
 * @Description: 首页控制层
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request){
        Object attribute = request.getSession().getAttribute(Constant.SESSION_USERID);
        if(null == attribute){
            return new ModelAndView("index").addObject("ServerResponse", ServerResponse.createBySuccessMessage("退出登陆成功"));
        }
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
    public ModelAndView favicon(){
        return new ModelAndView("favicon.ico");
    }
}
