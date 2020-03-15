package com.nextone.web.controller;

import com.nextone.pojo.Article;
import com.nextone.service.ArticleService;
import com.nextone.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @RequestMapping("/test")
    public String helloWolrd() {
        return "hello ~ Wolrd ";
    }

    /**
     * 提交添加请求请求
     *
     * @return
     */
    @RequestMapping("/addSubmit.do")
    public JsonResult addSubmit(Article model) {
        service.addSubmit(model);
        return JsonResult.ok("添加成功");
    }

    /**
     * 提交删除删除请求
     *
     * @return
     */
    @RequestMapping("/delete.do")
    public JsonResult delete(Article model) {
        service.delete(model);
        return JsonResult.ok("删除成功");
    }

    /**
     * 更新成功
     *
     * @return
     */
    @RequestMapping("/update.do")
    public JsonResult update(Article model) {
        service.update(model);
        return JsonResult.ok("更新成功");
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping("queryAll.do")
    public JsonResult queryAll() {
        return JsonResult.ok(service.queryAll());
    }

}