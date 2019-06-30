package com.nextone.framework.service;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-30
 * @description
 **/
public interface BaseService<T> {

    /**
     * 查询所有
     * @return
     */
    List<T> queryAll();

    /**
     * 分页 查询
     * @param page 页码
     * @param pageSize 每页的大小
     * @param map 条件
     * @return
     */
    PageResult queryAll(Integer page, Integer pageSize, Map map);


}
