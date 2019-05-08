package com.nextone.service.impl;

import com.nextone.mapper.UserMapper;
import com.nextone.pojo.User;
import com.nextone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-08
 * @description
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {

        return userMapper.queryAll();
    }

    @Override
    public User selectOne(Long id) {
        User user = new User();
        user.setId(id);
        return userMapper.selectOne(user);
    }
}
