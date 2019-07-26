package com.nextone.framework.service;

import com.nextone.utils.PageResult;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-24
 * @description
 **/
public interface CrudService<T,ID> {

     T selectOne(T entity);
     void deleteById(ID id);
     int deleteByEntity(T entity);
     int updateByEntity(T entity);
     void deleteBatch(ID[] ids);
     void save(T Entity);
     List<T> selectAll();
     T selectById(ID id);
     PageResult queryAll(Integer page, Integer pageSize);
}
