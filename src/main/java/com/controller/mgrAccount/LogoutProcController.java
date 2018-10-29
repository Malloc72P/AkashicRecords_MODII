package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutProcController {
	@RequestMapping("/hello/logout.do")
	public ModelAndView requestProcessor( HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView("mgr_account/logout");
		request.getSession().invalidate();
		return mav;
	}
	
}
