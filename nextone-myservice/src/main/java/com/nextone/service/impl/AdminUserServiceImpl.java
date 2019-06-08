package com.nextone.service.impl;

import com.nextone.mapper.AdminUserMapper;
import com.nextone.pojo.AdminUser;
import com.nextone.service.AdminUserService;
import com.nextone.utils.CommonUtils;
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
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public List<AdminUser> queryAll() {
        return adminUserMapper.selectAll();
    }

    @Override
    public AdminUser selectOne(Long id) {
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        return adminUserMapper.selectOne(adminUser);
    }

    @Override
    public AdminUser login(String username, String password) {
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser = adminUserMapper.selectOne(adminUser);
        if (adminUser != null) {
            // 盐加密码
            if (adminUser.getPassword().equalsIgnoreCase(CommonUtils.calculateMD5(adminUser.getSalt() + password))) {
                return adminUser;
            }
        }
        return null;
    }

    @Override
    public AdminUser findByUserName(String currentUsername) {
        return null;
    }
}
