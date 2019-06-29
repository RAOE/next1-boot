package com.nextone.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextone.mapper.PermissionMapper;
import com.nextone.pojo.Permission;
import com.nextone.service.PermissionService;
import com.nextone.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description 描述
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Permission> queryAll() {
        return permissionMapper.selectAll();
    }

    /**
     * 分页 查询
     * @param page
     * @param pageSize
     * @param map
     * @return
     */
    @Override
    public PageResult queryAll(Integer page, Integer pageSize, Map map) {
        PageHelper.startPage(page,pageSize);
        List<Permission> list=permissionMapper.selectAll();
        PageInfo<Permission> pageList=new PageInfo<Permission>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setRecords(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
    }
}
