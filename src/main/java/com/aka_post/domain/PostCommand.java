package com.aka_post.domain;

public class PostCommand {
	private int    post_id            ;
	private int    series_id          ;
	private String user_email         ;
	private int    img_id             ;
	private String post_title         ;
	private String post_content       ;
	private String post_regdate       ;
	private int    post_viewcount     ;
	private int    post_contentimglist;
	private String post_summary       ;
	
	
	
	public String getPost_summary() {
		return post_summary;
	}
	public void setPost_summary(String post_summary) {
		this.post_summary = post_summary;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getSeries_id() {
		return series_id;
	}
	public void setSeries_id(int series_id) {
		this.series_id = series_id;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPost_regdate() {
		return post_regdate;
	}
	public void setPost_regdate(String post_regdate) {
		this.post_regdate = post_regdate;
	}
	public int getPost_viewcount() {
		return post_viewcount;
	}
	public void setPost_viewcount(int post_viewcount) {
		this.post_viewcount = post_viewcount;
	}
	public int getPost_contentimglist() {
		return post_contentimglist;
	}
	public void setPost_contentimglist(int post_contentimglist) {
		this.post_contentimglist = post_contentimglist;
	}
	
	
}
