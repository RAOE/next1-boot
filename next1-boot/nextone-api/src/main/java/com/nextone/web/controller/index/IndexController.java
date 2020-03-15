package com.nextone.web.controller.index;

import com.nextone.pojo.SysMenu;
import com.nextone.service.SystemMenuService;
import com.nextone.utils.ImageCodeUtils;
import com.nextone.framework.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页的配置
 */
@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private SystemMenuService menuService;
    /**
     * 首页
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到首页")
    @GetMapping("/")
    public String index(ModelMap modelMap) {
        List<SysMenu> menus = menuService.selectAllMenus();
        menus.forEach(System.out::println);
        modelMap.put("menus", menus);
        return "index";
    }

    /**
     * 欢迎页面
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "欢迎页面")
    @GetMapping("/welcome")
    public ModelAndView welcome() {
        return new ModelAndView("/welcome");

    }
    /**
     * 登陆页面
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到登陆页面")
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }

    /**
     * 图片验证码
     *
     * @param response
     * @param request
     */
    @SysLog
    @RequestMapping("/imageCode")
    public void sendImageCode(HttpServletResponse response, HttpServletRequest request) {
        ImageCodeUtils.sendImageCode(request.getSession(), response);
    }

    /**
     * 错误页面
     * @return
     */
    @SysLog
    @ApiOperation(value = "错误页面")
    @GetMapping("/error")
    public ModelAndView error() {
        return new ModelAndView("/500");
    }


}
