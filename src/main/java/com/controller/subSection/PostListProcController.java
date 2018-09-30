package com.controller.subSection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostListProcController {
	
	@RequestMapping("/hello/postList.do")
	public ModelAndView requestProcessor() {
		ModelAndView mav = new ModelAndView("subSection/postList");
		
		return mav;
	}
	
}
