package com.nextone.mapper;

import com.nextone.pojo.Article;
import com.nextone.pojo.Permission;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface PermissionMapper extends Mapper<Permission>  , MySqlMapper<Permission> {
}
