package com.controller.mgrAccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;

@Controller
public class adminPageController {
	
	@Autowired
	UserDAO dao;
	
	@RequestMapping("/hello/adminPage.do")
	public ModelAndView requestProcessor( HttpServletRequest request ) {
		System.out.println("______________________________________________");
		System.out.println("adminPageController.requestProcessor >>> 메서드 호출됨"); 
		ModelAndView mav = new ModelAndView("mgr_account/adminPage");
		//name_input_submitPWCHK
		String pw	=	request.getParameter("adminPW");
		System.out.println("pw : "+pw);
		String email	=	(String)request.getSession().getAttribute("email");
		UserCommand userData = dao.getUserDataByEmail(email);
		boolean adminChecker = false;
		
		
		if(userData != null) {
			if(userData.getUser_password().equals(pw)  ) {
				//로그인 성공
				adminChecker	=	dao.checkSuperUser(userData.getUser_email());
			}
		}
		
		mav.addObject("adminChecker",adminChecker);
		
		System.out.println("______________________________________________"); 
		return mav; 
	}
}
