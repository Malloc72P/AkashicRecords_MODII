package com.controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.domain.UserCommand;

@Controller
public class MainController {
	
	
	@RequestMapping("/hello/mainPage.do")
	public ModelAndView requestProcessor(HttpServletRequest request) {
		System.out.println("_____________________________________________");
		System.out.println("MainController.requestProcessor >>> 매서드 호출됨");
		
		String      email   = null;
		UserCommand uData   = null;
		HttpSession session = null;
		
		if( request != null ) {
			session = request.getSession();
		}
		
		if(session != null) {
			if(session.getAttribute("email") != null) {
				email = (String)session.getAttribute("email");
			}
		}
		
		System.out.println("_____________________________________________");
		ModelAndView  mav = new ModelAndView("mainPage");
		mav.addObject("user_email", email);
		return mav;
	}
}
