package com.controller.mgrAccount;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class ProfileImgUploaderController {
	
	@RequestMapping("/hello/profileImgUploader.do")
	public ModelAndView requestProcessor(HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("mgr_account/profileImgUploader");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		return mav;
	}
}
