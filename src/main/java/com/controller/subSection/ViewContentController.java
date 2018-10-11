package com.controller.subSection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;

@Controller
public class ViewContentController {
	
	@Autowired
	private PostDAO dao;
	
	
	@RequestMapping("/hello/viewContent.do")
	public ModelAndView requestProcessor(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("subSection/viewContent");
		
		PostCommand post = dao.getPostById(request.getParameter("post_id"));
		
		mav.addObject("post",post);
		
		return mav;
	}
	
}