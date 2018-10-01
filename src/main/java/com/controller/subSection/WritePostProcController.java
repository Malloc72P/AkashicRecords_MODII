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
	
	@RequestMapping("/hello/writePost.do")
	public ModelAndView requestProcessor() {
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePost");
		
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
