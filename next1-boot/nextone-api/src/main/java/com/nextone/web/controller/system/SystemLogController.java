package com.nextone.web.controller.system;

import com.nextone.framework.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统日志
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description
 **/
@Controller
@RequestMapping("/systemlog")
public class SystemLogController {

    /**
     * 系统日志列表
     * @return
     */
    @SysLog
    @ApiOperation(value = "系统日志列表")
    @RequestMapping("/list")
    public ModelAndView list()
    {
        return new ModelAndView("/system/syslog/list");
    }







}
