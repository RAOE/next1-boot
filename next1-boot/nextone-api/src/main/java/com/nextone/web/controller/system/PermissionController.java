package com.nextone.web.controller.system;

import com.nextone.service.PermissionService;
import com.nextone.utils.JsonResult;
import com.nextone.web.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description
 **/
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 跳转到列表
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到菜单列表",notes = "跳转到菜单列表")
    @RequestMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/system/permission/list");
    }

    /**
     * 跳转到添加
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到菜单列表",notes = "跳转到菜单列表")
    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("/system/permission/add");
    }


    /**
     * 分页 查询权限能力
     * @param page
     * @param pageSize
     * @return
     */
    @SysLog
    @ApiOperation(value = "查询所有的权限能力",notes = "查询所有的权限能力")
    @RequestMapping("/queryAll")
    public JsonResult queryAll(Integer page, Integer pageSize) {
        return JsonResult.ok( permissionService.queryAll(page, pageSize, null));
    }
}
