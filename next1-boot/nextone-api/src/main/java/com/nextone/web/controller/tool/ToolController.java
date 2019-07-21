package com.nextone.web.controller.tool;

import com.nextone.web.annotation.SysLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-28
 * @description
 **/
@Controller
@RequestMapping("/tool")
public class ToolController {

    /**
     * 表单自动构建工具
     *
     * @return
     */
    @SysLog
    @RequestMapping("/build")
    public ModelAndView build() {

        return new ModelAndView("/tool/builder");

    }


}
