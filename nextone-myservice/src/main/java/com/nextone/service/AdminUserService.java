package com.nextone.service;

import com.nextone.pojo.AdminUser;
import com.nextone.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface AdminUserService {

    /**
     * 查询所有的用户列表
     * @return
     */
    List<AdminUser> queryAll();

    /**
     * 分页 查询所有的用户列表
     * @return
     */
    PageResult queryAll(Integer page, Integer pageSize, Map map);

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
