package com.nextone.web.api;

import com.nextone.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/3/6
 * @description 登陆相关
 **/
@RestController
@RequestMapping("/api/")
@Slf4j
public class LoginController {
    /**
     * 登陆实现
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public JsonResult login() {
        log.info("=====11111============login===========");
        return JsonResult.ok();
    }
}
