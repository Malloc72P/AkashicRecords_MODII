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
		Integer postNum = null;
		if( getSqlSession().selectOne("getMaxPostNum") != null ) {
			postNum = (Integer)getSqlSession().selectOne("getMaxPostNum") + 1;
		}
		else {
			postNum = 0;
		}
		return postNum;
	}

	@Override
	public PostCommand getPostById(String post_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getPostById", post_id);
	}

	@Override
	public String getUserNicknameByPostEmail(String post_email) {
		// TODO Auto-generated method stub
		return (String)getSqlSession().selectOne("getUserNicknameByPostEmail",post_email);
	}

	@Override
	public String getSeriesTitleByPostSeriesId(int post_seriesId) {
		// TODO Auto-generated method stub
		return (String)getSqlSession().selectOne("getSeriesTitleByPostSeriesId", post_seriesId);
	}

	@Override
	public boolean viewCountIncrementer(int post_id) {
		// TODO Auto-generated method stub
		int queryRes = getSqlSession().update("viewCount_Incrementer", post_id);
		if(queryRes != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public int getSeriesIdByPostId(int post_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("getSeriesIdByPostId", post_id);
	}

	@Override
	public boolean deletePostById(String post_id) {
		// TODO Auto-generated method stub
		int deleteChecker	=	getSqlSession().delete("deletePostById", post_id);
		if( deleteChecker != 0 ) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean deletePostBySeriesId(int series_id) {
		// TODO Auto-generated method stub
		int deleteChecker	=	getSqlSession().delete("deletePostBySeriesId", series_id);
		if(deleteChecker != 0) {
			return true;
		}
		else return false;
	}
	
	
	
}
