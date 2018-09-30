package com.controller.mgrAccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutProcController {
	@RequestMapping("/hello/logout.do")
	public ModelAndView requestProcessor() {
		ModelAndView mav = new ModelAndView("mgr_account/logout");
		return mav;
	}
	
}
