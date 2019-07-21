package com.nextone.service;

import com.nextone.framework.service.BaseService;
import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Permission;
import com.nextone.pojo.Role;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService extends BaseService<Role> {

    Set<String> queryAllRolesByUsername(AdminUser adminUser);

    Set<String> queryAllPermissionsByUsername(AdminUser adminUser);



}
