package com.aka_post.dao;

import java.util.List;

import com.aka_post.domain.PostCommand;

public interface PostDAO {
	public List			getPosts( int start, int end );
	public int			getPostCount();
	public int			getNewPostNum();
	public boolean		insertPost(PostCommand post);
	public PostCommand 	getPostById(String post_id);
	public String 		getUserNicknameByPostEmail(String post_email);
	public String 		getSeriesTitleByPostSeriesId(int post_seriesId);
	public boolean		viewCountIncrementer(int post_id);
	public int			getSeriesIdByPostId(int post_id);
	public boolean		deletePostById(String post_id);
	public boolean		deletePostBySeriesId( int series_id );
}
