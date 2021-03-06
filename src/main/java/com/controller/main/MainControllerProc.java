package com.controller.main;

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
import com.util.SessionMapMgr;

@Controller
public class MainControllerProc {
	
	@Autowired
	UserDAO userDao;
	
	@RequestMapping("/hello/mainPageProc.do")
	public ModelAndView requestProcessor(	HttpServletRequest request
											,HttpServletResponse response
											,@RequestParam(value="ssnId", defaultValue="") String req_ssnId) {
		System.out.println("_____________________________________________");
		System.out.println("MainControllerProc.requestProcessor >>> 매서드 호출됨");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		System.out.println("req ssnId : "+req_ssnId); 
		
		String      email   = null;
		UserCommand uData   = null;
		HttpSession session = null;
		boolean		adminCheck	=	false;
		boolean		loginCheck	=	false;
//		if( request != null ) {
//			System.out.println("세션 불러오기 성공");
//			session = request.getSession();
//		}
		if( !req_ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(req_ssnId);
		}
		else {
			session	=	request.getSession();
		}
		
		if(session != null) {
			
			if(session.getAttribute("email") != null) {
				System.out.println("기존 로그인 정보 식별");
				loginCheck	=	true;
				email 		= 	(String)session.getAttribute("email");
				adminCheck 	= 	userDao.checkSuperUser(email);
				System.out.println("adminCheck : "+adminCheck); 
			}else {
				System.out.println("기존 로그인 정보 식별 불가");
			}
		}
		
		System.out.println("_____________________________________________");
		ModelAndView  mav = new ModelAndView("mainPageProc");
		mav.addObject("user_email", email);
		mav.addObject("adminCheck", adminCheck);
		mav.addObject("loginCheck",	loginCheck);
		return mav;
	}
}
