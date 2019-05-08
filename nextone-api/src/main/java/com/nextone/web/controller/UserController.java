package com.nextone.web.controller;

import com.nextone.pojo.User;
import com.nextone.service.UserService;
import com.nextone.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @ApiOperation(value = "查询所有用户的信息", notes = "查询所有用户的信息")
    @GetMapping("/queryAll")
    public JsonResult queryAll() {
        List<User> list = userService.queryAll();
        for (User user : list) {
            System.out.println(user);
        }
        return JsonResult.ok(list);
    }
    @ResponseBody
    @ApiOperation(value = "查询单个用户的详细信息", notes = "根据url的id来查询用户信息")
    @GetMapping("/selectOne/{id}")
    public JsonResult selectOne(@PathVariable("id") Long id) {
        return JsonResult.ok(userService.selectOne(id));
    }

    @ApiOperation(value = "跳转到登陆页面")
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }
}