package com.nextone.service;

import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Role;
import com.nextone.utils.MyMapper;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService  {

    List<Role> queryAll();

    Set<String> queryAllRolesByUsername(AdminUser adminUser);

    Set<String> queryAllPermissionsByUsername(AdminUser adminUser);

    PageResult  queryAll(Integer page, Integer pageSize, Map map);
}
