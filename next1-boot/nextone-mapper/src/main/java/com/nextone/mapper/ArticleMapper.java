package com.nextone.mapper;
import com.nextone.pojo.AdminUser;
import com.nextone.pojo.Article;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


//author:xuyuanfeng
public interface  ArticleMapper extends Mapper<Article> , MySqlMapper<Article>
{

	

}