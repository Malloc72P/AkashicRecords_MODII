package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.util.SessionMapMgr;

@Controller
public class LogoutProcController {
	@RequestMapping("/hello/logout.do")
	public ModelAndView requestProcessor( 	HttpServletResponse response
											,HttpServletRequest request
											,@RequestParam(value="ssnId", defaultValue="") String req_ssnId
										) {
		ModelAndView mav = new ModelAndView("mgr_account/logout");
		response.setHeader("Access-Control-Allow-Origin","*");
		boolean logoutChecker	=	false;
		HttpSession	session	=	null;
		if( !req_ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(req_ssnId);
			if(session != null) {
				session.invalidate();	
			}
			logoutChecker	=	true;
		}else {
			logoutChecker	=	false;
		}
		
		//request.getSession().invalidate();
		mav.addObject("logoutChecker",true);
		return mav;
	}
	
}
