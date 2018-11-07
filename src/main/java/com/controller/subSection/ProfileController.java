package com.controller.subSection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
	
	@RequestMapping("/hello/profile.do")
	public ModelAndView requestProcessor( HttpServletResponse response ) {
		ModelAndView mav = new ModelAndView("subSection/profile");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		return mav;
	}
	
}
