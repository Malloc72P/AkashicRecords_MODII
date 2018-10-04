package com.aka_post.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_post.domain.PostCommand;
import com.aka_post.domain.StartAndEnd;

import constSet.MainConst;

public class Impl_PostDAO extends SqlSessionDaoSupport implements PostDAO {

	@Override
	public List getPosts(int start, int end) {
		// TODO Auto-generated method stub
		StartAndEnd sae = new StartAndEnd(start, end);
		List articleList = (List)getSqlSession().selectList("getPosts", sae);
		
		return articleList;
	}

	@Override
	public int getPostCount() {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getPostCount");
	}

	@Override
	public boolean insertPost(PostCommand post) {
		// TODO Auto-generated method stub
		int insertRES = getSqlSession().insert("insertPost", post);
		boolean insertChecker = false;
		if(insertRES != 0) {
			insertChecker = true;
		}
		else {
			insertChecker = false;
		}
		return insertChecker;
	}

	@Override
	public int getNewPostNum() {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getMaxPostNum") + 1;
	}
	
}
