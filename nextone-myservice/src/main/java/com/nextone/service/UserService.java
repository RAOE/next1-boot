package com.nextone.service;

import com.nextone.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有的用户列表
     * @return
     */
    List<User> queryAll();

    /**
     * 查询单个用户信息
     * @param id
     * @return
     */
    User selectOne(Long id);

}
