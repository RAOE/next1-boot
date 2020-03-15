package com.nextone.web.controller;


import com.nextone.framework.web.BaseController;
import com.nextone.pojo.Project;
import com.nextone.service.ProjectService;
import com.nextone.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {
    public ProjectController() {
        basePathPrefix = "/system/project";
    }

    @Autowired
    private ProjectService service;

    @RequestMapping("/test")
    @ResponseBody
    public String helloWolrd() {
        return "hello ~ Wolrd ";
    }

    /**
     * 提交添加请求请求
     *
     * @return
     */
    @RequestMapping("/addSubmit.do")
    @ResponseBody
    public JsonResult addSubmit(Project model) {
        service.addSubmit(model);
        return JsonResult.ok("添加成功");
    }

    /**
     * 提交删除删除请求
     *
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public JsonResult delete(Project model) {
        service.delete(model);
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新成功
     *
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public JsonResult update(Project model) {
        service.update(model);
        return JsonResult.ok("更新成功");
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("queryAll.do")
    @ResponseBody
    public JsonResult queryAll(Integer page,Integer pageSize) {
        return JsonResult.ok(service.queryAll(page,pageSize));
    }

}