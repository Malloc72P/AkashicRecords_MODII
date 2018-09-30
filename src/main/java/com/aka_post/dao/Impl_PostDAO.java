package com.aka_post.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_post.domain.PostCommand;
import com.aka_post.domain.StartAndEnd;

import constSet.MainConst;

public class Impl_PostDAO extends SqlSessionDaoSupport implements PostDAO {

	@Override
	public List getArticles(int start, int end) {
		// TODO Auto-generated method stub
		StartAndEnd sae = new StartAndEnd(start, end);
		List articleList = getSqlSession().selectList("getArticles", sae);
		
		return articleList;
	}

	@Override
	public int getArticleCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getArticleCount");
	}
	
}
