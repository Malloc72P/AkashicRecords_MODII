package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;

@Controller
public class LoginProcController {
	@Autowired
	private UserDAO dao ;
	
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/loginProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,@RequestParam(value="email" , defaultValue="") String user_email
            							 ,@RequestParam(value="password" , defaultValue="") String user_password) {
		System.out.println("_____________________________________________");
		System.out.println("loginProcController.requestProcessor >>> 매서드 호출됨");
		System.out.println("loginProcController.requestProcessor >>> user_email : "+user_email);
		System.out.println("loginProcController.requestProcessor >>> user_password : "+user_password);
		
		System.out.println("loginProcController.requestProcessor >>> dao접근 전");
		UserCommand userdata = dao.getUserDataByEmail(user_email);
		System.out.println("loginProcController.requestProcessor >>> dao접근 후");
		System.out.println("loginProcController.requestProcessor >>> dao.getEmail : "+userdata.getUser_email());
		
		boolean loginChecker = false;
		
		if(userdata.getUser_password().equals(user_password)) {
			System.out.println("loginProcController.requestProcessor >>> 패스워드 일치");
			loginChecker = true;
			request.getSession().setAttribute("loginChecker", loginChecker);
			request.getSession().setAttribute("email"       , user_email);
			System.out.println("loginProcController.requestProcessor >>> 세션 저장 성공");
		}
		
		System.out.println("_____________________________________________");
		ModelAndView mav = new ModelAndView("mgr_account/loginProc");
		System.out.println("loginProcController.requestProcessor >>> mav view : "+mav.getViewName());
		return mav; 
	}
}
