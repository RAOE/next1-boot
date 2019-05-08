package com.nextone.mapper;

import com.nextone.pojo.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import java.util.List;


public interface UserMapper extends Mapper<User>, MySqlMapper<User> {

    List<User> queryAll();

}
