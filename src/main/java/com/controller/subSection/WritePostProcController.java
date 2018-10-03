package com.controller.subSection;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;

@Controller
public class WritePostProcController {
	
	@Autowired
	private PostDAO dao;
	
	@RequestMapping("/hello/writePostProc.do")
	public ModelAndView requestProcessor(
			                             @RequestParam("post_title")   String post_title    ,
										 @RequestParam("post_content") String post_content,
										 @RequestParam("series_id")    String series_id 
										)
	{
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePostProc");
		System.out.println("WritePostProcController.requestProcessor >>> post_title   : "+post_title);
		System.out.println("WritePostProcController.requestProcessor >>> post_content : "+post_content);
		System.out.println("WritePostProcController.requestProcessor >>> series_id    : "+series_id);
		mav.addObject("saveChecker","true");
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
