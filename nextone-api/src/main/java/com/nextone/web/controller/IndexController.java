package com.nextone.web.controller;

import com.nextone.utils.ImageCodeUtils;
import com.nextone.web.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class IndexController {

    @SysLog
    @ApiOperation(value = "跳转到首页")
    @GetMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("/index/index");
    }

    @SysLog
    @ApiOperation(value = "欢迎页面")
    @GetMapping("/welcome")
    public ModelAndView welcome()
    {
        return new ModelAndView("/index/welcome");

    }
    /**
     * 图片验证码
     * @param response
     * @param request
     */
    @SysLog
    @RequestMapping("/imageCode.do")
    public void sendImageCode(HttpServletResponse response, HttpServletRequest request) {
        ImageCodeUtils.sendImageCode(request.getSession(), response);
    }

}
