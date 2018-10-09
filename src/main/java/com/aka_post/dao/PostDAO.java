package com.aka_post.dao;

import java.util.List;

import com.aka_post.domain.PostCommand;

public interface PostDAO {
	public List getPosts( int start, int end );
	public int getPostCount();
	public int getNewPostNum();
	public boolean insertPost(PostCommand post);
	public PostCommand getPostById(String post_id);
}
