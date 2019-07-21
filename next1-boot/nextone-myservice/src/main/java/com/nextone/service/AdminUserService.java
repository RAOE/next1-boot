package com.nextone.service;

import com.nextone.framework.service.BaseService;
import com.nextone.pojo.AdminUser;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface AdminUserService  extends BaseService<AdminUser> {


    /**
     * 查询单个用户信息
     * @param id
     * @return
     */
    AdminUser selectOne(Long id);

    /**
     * 模拟登陆
     * @param username
     * @param password
     * @return
     */
    AdminUser login(String username, String password);

    /**
     * 根据名称查询出用户
     * @param currentUsername
     * @return
     */
    AdminUser findByUserName(String currentUsername);






}
