package com.nextone.service;
import com.nextone.pojo.Article;

import java.util.List;

//author:xuyuanfeng
public interface ArticleService
{
  
   public void addSubmit(Article modelName);
   public void update(Article modelName);
   public List<Article> queryAll();
   public void delete(Article id);

}