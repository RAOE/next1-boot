package com.nextone.framework.serviceImp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextone.framework.MyMapper;
import com.nextone.framework.service.CrudService;
import com.nextone.utils.PageResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-07-23
 * @description 通用的crud service 用于拓展 service  第一层 实现简单的crud方法 需要继承 MyMapper
 * 注意 有些service 可能用不到 crud  那么请选择 BaseServiceImpl 通用的业务抽离 解耦合
 **/
@Slf4j
public abstract class CrudServiceImpl<T, S extends MyMapper<T>, ID extends Serializable> implements CrudService<T, ID> {

    @Autowired
    protected S mapper;

    protected Class<T> tClass;

    /**
     * 查询出一条数据
     *
     * @param entity
     * @return
     */
    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    /**
     * 删除一条数据 根据id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(ID id) {
        try {
            val entity = tClass.newInstance();
            BeanUtils.setProperty(entity, "id", id);
            mapper.deleteByPrimaryKey(entity);
        } catch (InstantiationException e) {
            throw new RuntimeException("删除一条数据 创建对象" + tClass + "发生了错误");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("删除一条数据 创建对象" + tClass + "发生了错误");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("BeanUtils设置对象的id值发生了错误");
        }

    }
    /**
     * 删除一条数据
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByEntity(T entity) {
        return mapper.deleteByPrimaryKey(entity);
    }

    /**
     * 更新不为null的字段
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByEntity(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 批量删除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(ID[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            return;
        }
        try {
            for (ID id : ids) {
                val entity = tClass.newInstance();
                //再通过BeanUtils来给对象设置id值
                BeanUtils.setProperty(entity, "id", id);
                mapper.deleteByPrimaryKey(entity);
            }
        } catch (InstantiationException e) {
            throw new RuntimeException("批量删除 创建对象" + tClass + "发生了错误");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("批量删除 创建对象" + tClass + "发生了错误");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("BeanUtils设置对象的id值发生了错误");
        }
    }

    /**
     * 插入一条数据到数据库
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T entity) {
        mapper.insertSelective(entity);
    }

    /**
     * 查询出所有的数据
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /***
     * 根据id查询出一条数据
     * @param id
     * @return
     */
    @Override
    public T selectById(ID id) {
        try {
            val entity = tClass.newInstance();
            BeanUtils.setProperty(entity, "id", id);
            return mapper.selectOne(entity);
        } catch (InstantiationException e) {
            throw new RuntimeException(" 创建对象" + tClass + "发生了错误");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(" 创建对象" + tClass + "发生了错误");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("BeanUtils设置对象的id值发生了错误");
        }
    }
    /**
     * 分页查询所有数据
     * @param page
     * @param pageSize
     */
    @Override
    public PageResult queryAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<T> list= mapper.selectAll();
        PageInfo<T> pageList = new PageInfo<T>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setRecords(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
    }
}
