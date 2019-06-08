package com.nextone.service.impl;

import com.nextone.mapper.PermissionMapper;
import com.nextone.pojo.Permission;
import com.nextone.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.selectAll();
    }
}
