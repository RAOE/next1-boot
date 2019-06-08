package com.nextone.service;

import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Role;
import com.nextone.utils.MyMapper;

import java.util.List;
import java.util.Set;

public interface RoleService  {

    public List<Role> queryAll();

    Set<String> queryAllRolesByUsername(AdminUser adminUser);

    Set<String> queryAllPermissionsByUsername(AdminUser adminUser);
}
