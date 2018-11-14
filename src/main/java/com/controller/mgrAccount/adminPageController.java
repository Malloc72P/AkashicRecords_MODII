package com.controller.mgrAccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;
import com.util.SessionMapMgr;

@Controller
public class adminPageController {
	
	@Autowired
	UserDAO dao;
	
	@RequestMapping("/hello/adminPage.do")
	public ModelAndView requestProcessor(	HttpServletRequest request
											,HttpServletResponse response
											,@RequestParam(value="password", defaultValue="") String password
											,@RequestParam(value="ssnId", defaultValue="") String ssnId) {
		System.out.println("______________________________________________");
		System.out.println("adminPageController.requestProcessor >>> 메서드 호출됨"); 
		ModelAndView mav = new ModelAndView("mgr_account/adminPage");
		response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Origin", "*"); 
		
		HttpSession session = null;
		if( !ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
			if( session == null ) {
				mav.addObject("adminChecker","invalidSession");
				return mav;
			}
			else {
				if( !dao.checkSuperUser( (String)session.getAttribute("email") ) ) {
					mav.addObject("adminChecker","lowAuthorize");
					return mav;	
				}
			}
			
		}
		else {
			mav.addObject("adminChecker","invalidSession");
			return mav;
		}
		if( password.equals("") ) {
			mav.addObject("adminChecker","noArgument");
			return mav;
		}
		
		//name_input_submitPWCHK
		System.out.println("password : "+password);
		if(session.getAttribute("email") == null) {
			mav.addObject( "validator",	 false );
			mav.addObject( "activation", false );
			return mav;
		}
		String email	=	(String)session.getAttribute("email");
		UserCommand userData = dao.getUserDataByEmail(email);
		boolean adminChecker = false;
		
		
		if(userData != null) {
			if(userData.getUser_password().equals(password)  ) {
				//로그인 성공
				adminChecker	=	dao.checkSuperUser(userData.getUser_email());
			}
		}
		
		mav.addObject("adminChecker",adminChecker);
		
		System.out.println("______________________________________________"); 
		return mav; 
	}
}
