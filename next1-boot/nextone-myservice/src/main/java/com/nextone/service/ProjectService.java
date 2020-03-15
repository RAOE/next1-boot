package com.nextone.service;
import com.nextone.pojo.Project;
import com.nextone.utils.PageResult;

import java.util.List;

//author:xuyuanfeng
public interface ProjectService
{
  
   public void addSubmit(Project modelName);
   public void update(Project modelName);
   public PageResult queryAll(Integer page, Integer pageSize);
   public void delete(Project id);

}