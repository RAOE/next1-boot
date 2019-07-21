package com.nextone.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextone.framework.serviceImp.BaseServiceImpl;
import com.nextone.mapper.AdminUserMapper;
import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Permission;
import com.nextone.service.AdminUserService;
import com.nextone.utils.CommonUtils;
import com.nextone.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-08
 * @description
 **/
@Service
public class AdminUserServiceImpl extends BaseServiceImpl<AdminUser> implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public List<AdminUser> queryAll() {
        return adminUserMapper.selectAll();
    }

    @Override
    public PageResult queryAll(Integer page, Integer pageSize, Map map) {
        PageHelper.startPage(page, pageSize);
        List<AdminUser> list = adminUserMapper.selectAll();
        PageInfo<AdminUser> pageList = new PageInfo<AdminUser>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setRecords(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
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


        return adminUserMapper.findByUserName(currentUsername);
    }
}
