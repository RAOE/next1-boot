package com.nextone.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextone.pojo.Project;
import com.nextone.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.nextone.mapper.ProjectMapper;
import com.nextone.service.ProjectService;
import java.util.List;
//author:xuyuanfeng
@Service
public class ProjectServiceImp implements ProjectService
{
    @Autowired
    private ProjectMapper mapper;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSubmit(Project model) {
		mapper.insert(model);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Project model) {
		mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageResult queryAll(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Project> list = mapper.selectAll();
		PageInfo<Project> pageList = new PageInfo<Project>(list);
		PageResult pageResult = new PageResult();
		pageResult.setPage(page);
		pageResult.setRecords(pageList.getPages());
		pageResult.setRows(list);
		pageResult.setRecords(pageList.getTotal());
		return pageResult;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Project  model) {
        mapper.delete(model);
	}
}