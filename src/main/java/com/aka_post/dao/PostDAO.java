package com.aka_post.dao;

import java.util.List;

public interface PostDAO {
	public List getArticles( int start, int end );
	public int getArticleCount();
	
}
