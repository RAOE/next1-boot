package com.nextone.serviceImp;
import com.nextone.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.nextone.mapper.ArticleMapper;


import com.nextone.service.ArticleService;

import java.util.List;

//author:xuyuanfeng
@Service
public class ArticleServiceImp implements ArticleService
{
    @Autowired
    private ArticleMapper mapper;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addSubmit(Article model) {
		mapper.insert(model);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Article model) {
		mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Article> queryAll() {
		return mapper.selectAll();
		}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Article  model) {
        mapper.delete(model);
	}


}