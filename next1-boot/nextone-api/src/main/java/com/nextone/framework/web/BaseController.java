package com.nextone.framework.web;

import com.nextone.framework.service.BaseService;
import com.nextone.web.annotation.SysLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description 用于拓展controller 第二层
 **/
@Controller
public abstract class BaseController<T, S extends BaseService<T>> extends SimpleController {

    /**
     * 统一日期转换处理
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,
                true));// CustomDateEditor为自定义日期编辑器
    }
    /**
     * 基础路径
     */
    protected String basePathPrefix;
    /**
     * 添加
     */
    protected String PAGE_ADD = "/add";
    /**
     * 列表
     */
    protected String PAGE_LIST = "/list";
    /**
     * 显示详情
     */
    protected String PAGE_DETAILS = "/details";
    /**
     * 编辑
     */
    protected String PAGE_EDIT = "/edit";
    /**
     * 错误叶脉你
     */
    protected String PAGE_ERROR="error/500";

    /**
     * 跳转到添加页面
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到添加页面", notes = "跳转到添加页面")
    @RequestMapping("/add")
    public String add() {
        return basePathPrefix + PAGE_ADD;
    }

    /**
     * 跳转到编辑页面
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到编辑页面", notes = "跳转到编辑页面")
    @RequestMapping("/edit")
    public String edit() {
        return basePathPrefix + PAGE_EDIT;
    }

    /**
     * 跳转到列表
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到列表", notes = "跳转到列表")
    @RequestMapping("/list")
    public String list() {
        return basePathPrefix + PAGE_LIST;
    }

    /**
     * 跳转到列表
     *
     * @return
     */
    @SysLog
    @ApiOperation(value = "跳转到详情页面", notes = "跳转到详情页面")
    @RequestMapping("/details")
    public String details() {
        return basePathPrefix + PAGE_DETAILS;
    }


//    protected SpringDataWebProperties.Pageable getPageRequest() {
//        String offsetStr = request.getParameter("offset");
//        String limitStr = request.getParameter("limit");
//        if (StringKit.isBlank(offsetStr) && StringKit.isBlank(limitStr)) {
//            // 表示不分页
//            return null;
//        } else {
//            int offset = 0;
//            int limit = 5;// 相当于pageSize
//            int pageNumber = 0;// 如果是pageRequest对象，页码起始值为0
//            if (StringKit.isNotBlank(limitStr)) {
//                limit = Integer.valueOf(limitStr);
//            }
//            if (StringKit.isNotBlank(offsetStr)) {
//                // 转为页码
////				offset = Integer.valueOf(offsetStr);
////				pageNumber = offset / limit;
//                  offset = Integer.valueOf(offsetStr);
//                pageNumber = offset - 1;
//            }
//            return PageRequest.of(pageNumber, limit, getSort());
//        }
//    }

}
