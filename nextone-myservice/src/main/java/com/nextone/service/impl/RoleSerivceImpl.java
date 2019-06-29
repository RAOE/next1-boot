package com.nextone.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextone.mapper.RoleMapper;
import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Role;
import com.nextone.service.RoleService;
import com.nextone.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页的数据量
     * @param map 条件
     * @return
     */
    @Override
    public PageResult queryAll(Integer page, Integer pageSize, Map map) {
        PageHelper.startPage(page,pageSize);
        List<Role> list=roleMapper.selectAll();
        PageInfo<Role> pageList=new PageInfo<Role>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setRecords(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
    }
}
