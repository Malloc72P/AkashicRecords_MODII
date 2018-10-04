package com.controller.subSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
		
		System.out.println("***** before loop *****");
		for(Element el : tagBody.select("img")) {
			System.out.println("WritePostProcController.requestProcessor >>> el : "+el);
		}
		
		PostCommand post = new PostCommand();
		post.setPost_id(dao.getNewPostNum());
		post.setPost_title(post_title);
		post.setPost_content(post_content);
		post.setPost_viewcount(0);
		post.setUser_email((String)request.getSession().getAttribute("email"));
		post.setSeries_id(series_id);
		post.setImg_id(2);
		post.setPost_contentimglist(0);
		
		boolean insertChecker = dao.insertPost(post);
		
		mav.addObject("insertChecker",insertChecker);
		
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
