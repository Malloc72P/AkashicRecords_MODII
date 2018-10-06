package com.controller.subSection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class ImageUploaderController {
	
	@RequestMapping("/hello/imageUploader.do")
	public ModelAndView requestProcessor() {
		ModelAndView mav = new ModelAndView("subSection/imageUploader");
		
		return mav;
	}
}
