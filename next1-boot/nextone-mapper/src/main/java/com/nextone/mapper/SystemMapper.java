package com.nextone.mapper;

import com.nextone.framework.MyMapper;
import com.nextone.pojo.Project;
import com.nextone.pojo.SysMenu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SystemMapper extends Mapper<SysMenu> , MySqlMapper<SysMenu> {
}
