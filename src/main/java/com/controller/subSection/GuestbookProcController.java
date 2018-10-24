package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;

@Controller
public class GuestbookProcController {
	
	@Autowired
	guestbookDAO dao;
	
	@RequestMapping("/hello/guestBookProc.do")
	public ModelAndView requestProcessor(	@RequestParam("gbMsg") String gbMsg,
											HttpServletRequest request
										) {
		System.out.println("_____________________________________________________");
		System.out.println("GuestbookProcController.requestProcessor >>> 메서드 호출됨");
		
		HttpSession 	session 			= request.getSession();
		String			loggedInUserEmail 	= (String)session.getAttribute("email");
		ModelAndView	mav 				= new ModelAndView("subSection/guestBookProc");
		
		
		System.out.println("gbMsg : "+gbMsg);
		System.out.println("logged In email : "+loggedInUserEmail);
		boolean insertChecker = false;
		
		GB_Guest_MsgCommand guestMsg = new GB_Guest_MsgCommand();
		guestMsg.setGb_writer_email(loggedInUserEmail);
		guestMsg.setGb_content(gbMsg);
				
		insertChecker = dao.insertGuestMsg(guestMsg);
		
		mav.addObject("insertChecker",insertChecker);
		System.out.println("_____________________________________________________");
		return mav;
	}
	
}
