package com.love.house.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**测试表现层
 * @author wy
 */
@RestController
public class TestController {

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

}
