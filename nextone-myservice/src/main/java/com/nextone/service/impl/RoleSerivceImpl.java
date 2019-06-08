package com.nextone.service.impl;

import com.nextone.mapper.RoleMapper;
import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Role;
import com.nextone.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-06-08
 * @description
 **/
@Service
public class RoleSerivceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryAll() {
        return roleMapper.selectAll();
    }

    @Override
    public Set<String> queryAllRolesByUsername(AdminUser adminUser) {
        return null;
    }

    @Override
    public Set<String> queryAllPermissionsByUsername(AdminUser adminUser) {
        return null;
    }
}
