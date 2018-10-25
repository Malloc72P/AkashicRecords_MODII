package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Admin_MsgCommand;

@Controller
public class GuestBookReplyProcController {

	@Autowired
	private guestbookDAO dao;
	
	@RequestMapping("/hello/guestBookReplyProc.do")
	public ModelAndView requestProccessor( 	@RequestParam("gbReplyMsg") String msg ,
			 								@RequestParam("gbReplyMsg_id") int msg_id,
			 								HttpServletRequest request
			 ) {
		System.out.println("___________________________________________________________");
		System.out.println("GuestBookReplyProcController.requestProccessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/guestBookReplyProc");
		
		boolean insertChecker = false;
		System.out.println("msg : "+msg);
		System.out.println("msg_id : "+msg_id); 
		
		GB_Admin_MsgCommand adminMsg = new GB_Admin_MsgCommand();
		adminMsg.setGb_writer_email((String)request.getSession().getAttribute("email"));
		adminMsg.setGb_content(msg);
		adminMsg.setGb_to_guest_id(msg_id);
		
		insertChecker = dao.insertAdminMsg(adminMsg);
		
		mav.addObject("insertChecker", insertChecker);
		System.out.println("___________________________________________________________");
		return mav;
	}
	
}
