package com.nextone.mapper;

import com.nextone.pojo.AdminUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import java.util.List;


public interface AdminUserMapper extends Mapper<AdminUser>, MySqlMapper<AdminUser> {

    List<AdminUser> queryAll();

}
