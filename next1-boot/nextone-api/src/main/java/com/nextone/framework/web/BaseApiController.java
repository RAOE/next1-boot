package com.nextone.framework.web;

import com.nextone.framework.service.CrudService;
import com.nextone.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;


/**
 * 通用的rest接口 用于拓展 api controller  在继承层级中 第二层
 * 懒人必备 ....!!!!
 *
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description
 **/
@RestController
public abstract class BaseApiController<T, S extends CrudService<T, ID>, ID extends Serializable> extends SimpleController {
    /**
     * 注入service 层 实现通用的crud方法
     */
    @Autowired
    protected S service;
    /**
     * 插入
     * entity
     * @return
     */
    @RequestMapping("/save")
    @ApiOperation(value = "通用的api插入接口", notes = "插入一条数据")
    public JsonResult save(T entity) {
        service.save(entity);
        return JsonResult.ok();
    }
    /**
     * 更新  不为null的字段
     *
     * @param entity
     * @return
     */
    @RequestMapping("/update")
    @ApiOperation(value = "通用的更新接口", notes = "更新一条数据")
    public JsonResult update(T entity) {
        service.updateByEntity(entity);
        return JsonResult.ok();
    }
    //删除功能暂时不启用
//    /**
//     * 根据id删除一条数据
//     *
//     * @param id
//     * @return
//     */
//    @RequestMapping("/deleteById/{id}")
//    @ApiOperation(value = "通用的删除接口", notes = "根据id来删除一条数据")
//    public JsonResult deleteById(@PathVariable("id") ID id) {
//        service.deleteById(id);
//        return JsonResult.ok();
//    }
//    /**
//     * 删除一条数据
//     * @param entity
//     * @return
//     */
//    @RequestMapping("/deleteEntity")
//    public JsonResult deleteEntity(T entity) {
//        return JsonResult.ok(service.deleteByEntity(entity));
//    }

    /**
     * 查询出所有的数据
     *
     * @return
     */
    @RequestMapping("/selectAll")
    @ApiOperation(value = "通用的查询接口", notes = "查询出所有的表数据")
    public JsonResult selectAll() {
        return JsonResult.ok(service.selectAll());
    }

    /**
     * 根据id查询出一条数据
     *
     * @return
     */
    @RequestMapping("/selectOne/{id}")
    @ApiOperation(value = "通用的查询接口", notes = "查询出所有的表数据")
    public JsonResult selectOne(@PathVariable("id") ID id) {
        return JsonResult.ok(service.selectById(id));
    }
    /**
     * 分页 查询出所有数据 与 bootstrap-table整合 !!!
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public JsonResult queryAll(Integer page,Integer pageSize)
    {
        return JsonResult.ok(service.queryAll(page,pageSize));
    }

}
