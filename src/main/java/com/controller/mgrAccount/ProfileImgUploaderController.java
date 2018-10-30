package com.controller.mgrAccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class ProfileImgUploaderController {
	
	@RequestMapping("/hello/profileImgUploader.do")
	public ModelAndView requestProcessor() {
		ModelAndView mav = new ModelAndView("mgr_account/profileImgUploader");
		
		return mav;
	}
}
