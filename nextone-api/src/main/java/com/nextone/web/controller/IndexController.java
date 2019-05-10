package com.nextone.web.controller;

import com.nextone.utils.ImageCodeUtils;
import com.nextone.web.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("")
public class IndexController {

    @SysLog
    @GetMapping("/index")
    public String index() {
        return "hello nextone~!";
    }
    @SysLog
    @GetMapping("/")
    public String welcome()
    {
        return "welcome to use nextone-srpingboot";
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
