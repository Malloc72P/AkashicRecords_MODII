package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;

@Controller
public class pwCheckProcController {

	@Autowired
	UserDAO dao;
	
	@RequestMapping("/hello/pwCheckProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request,
			                              @RequestParam("user_password") String user_password ) {
		System.out.println("________________________________________________________");
		System.out.println("pwCheckProcController.requestProcessor >>> 매서드 호출됨");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView("subSection/pwCheckProc");
		String user_email   = "";
		boolean validator = false;
		
		if(session.getAttribute("email") != null) {
			user_email = (String)session.getAttribute("email");
			UserCommand user = dao.getUserDataByEmail(user_email);
			
			if(user.getUser_password().equals(user_password)) {
				validator = true;
			}
			else {
				validator = false;
			}
		}
		else {
			validator = false;
		}
		System.out.println("pwCheckProcController.requestProcessor >>> validator : "+validator);
		
		
		mav.addObject( "validator", validator );
		System.out.println("________________________________________________________");
		return mav;
	}
	
}