package com.nextone.service;

import com.nextone.pojo.Permission;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface PermissionService   {
    /**
     * 查询所有菜单
     * @return
     */
     List<Permission> queryAll();

    /**
     * 分页查询所有的菜单信息
     * @param page
     * @param pageSize
     * @param map
     * @return
     */
     PageResult queryAll(Integer page, Integer pageSize, Map map);

}
