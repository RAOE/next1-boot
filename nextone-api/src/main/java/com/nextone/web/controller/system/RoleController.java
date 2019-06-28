package com.nextone.web.controller.system;

import com.nextone.service.RoleService;
import com.nextone.utils.JsonResult;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */
    @ApiOperation(value = "查询所有的角色")
    @RequestMapping("/queryAll")
    public JsonResult queryAll() {
        return JsonResult.ok(roleService.queryAll());
    }

    /**
     * 权限列表
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list()
    {
        return new ModelAndView("/system/role/list");
    }


}
