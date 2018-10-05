package com.controller.subSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;

@Controller
public class WritePostProcController {
	
	@Autowired
	private PostDAO dao;
	
	@RequestMapping("/hello/writePostProc.do")
	public ModelAndView requestProcessor(
										 HttpServletRequest request,
			                             @RequestParam("post_title")   String post_title    ,
										 @RequestParam("post_content") String post_content,
										 @RequestParam("series_id")    int    series_id 
										)
	{
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePostProc");
		System.out.println("WritePostProcController.requestProcessor >>> post_title   : "+post_title);
		System.out.println("WritePostProcController.requestProcessor >>> post_content : "+post_content);
		System.out.println("WritePostProcController.requestProcessor >>> series_id    : "+series_id);
		
		Document contentDOC = Jsoup.parse(post_content); 
		Element tagBody = contentDOC.body();
		
		
		System.out.println("***** HTML BODY *****");
		System.out.println(tagBody);
		
		System.out.println("***** IMAGE PARSER *****");
		for(Element el : tagBody.select("img")) {
			System.out.println("WritePostProcController.requestProcessor >>> el : "+el);
		}
		System.out.println("***** TEXT PARSER *****");
		String post_summary = tagBody.text();
		int post_summary_length = post_summary.getBytes().length;
		System.out.println("text : "+post_summary );
		System.out.println("TAG_BODY_LENGTH : "+tagBody.toString().length());
		System.out.println("post_summary_LENGTH : "+post_summary_length);
				
		if(post_summary_length > 1000) {
			StringBuffer buff = new StringBuffer( post_summary.substring(0, 500) ); 
			post_summary  = new String( buff.toString() );
					
			System.out.println("modified post_summary_LENGTH : "+post_summary.getBytes().length);
		}
				
		
		
		
		
		
		PostCommand post = new PostCommand();
		
		post.setPost_id(dao.getNewPostNum());
		post.setPost_title(post_title);
		post.setPost_content(post_content);
		post.setPost_summary(post_summary);
		post.setPost_viewcount(0);
		post.setUser_email((String)request.getSession().getAttribute("email"));
		post.setSeries_id(series_id);
		post.setImg_id(2);
		post.setPost_contentimglist(0);
		
		
		boolean insertChecker = false;
		insertChecker = dao.insertPost(post);
		
		
		mav.addObject("insertChecker",insertChecker);
		
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
