package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_user.dao.UserDAO;
import com.util.SessionMapMgr;

@Controller
public class DeleteGbReplyController {
	
	@Autowired
	private guestbookDAO dao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/hello/deleteAdminMsg.do")
	public ModelAndView requestProcessor( 
											HttpServletRequest 		request
											,HttpServletResponse 	response
											,@RequestParam(value="ssnId",		defaultValue="") String ssnId
											,@RequestParam(value="msgId", 		defaultValue="") int msgId
											,@RequestParam(value="originId", 	defaultValue="") int originId
											) {
		ModelAndView mav = new ModelAndView("subSection/deleteGbMsg");
		System.out.println("______________________________________________________");
		System.out.println("DeleteGbMsgController.requestProcessor >>> 메서드 호출됨"); 
		String	deleteChecker	=	"false";
		System.out.println("ssnId 		: "+ssnId);
		System.out.println("msgId 		: "+msgId);
		System.out.println("originId 	: "+originId);
		HttpSession	session	=	null;
		if( !ssnId.equals("") ) {
			//ssnID를 받은 경우
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
			if( session == null ) {
				//세션이 서버에서 이미 만료된 경우
				mav.addObject("deleteChecker","invalidSession");
				System.out.println("세선만료");
				return mav;
			}
			
			
		}
		else {
			//ssnID를 못받은경우
			mav.addObject("deleteChecker","invalidSession");
			System.out.println("세선만료");
			return mav;
		}
		
		String userEmail	=	(String)session.getAttribute("email");	
		GB_Admin_MsgCommand msgObj	=	dao.getAdminMsgById(msgId);
		if(msgObj == null) {
			deleteChecker	=	"false";
		}
		else {
			if( msgObj.getGb_writer_email().equals(userEmail) || userDao.checkSuperUser(userEmail) ) {
				boolean sqlChecker	=	dao.deleteReplyById(msgId);
				if(sqlChecker) {
					deleteChecker	=	"true";
					dao.resetReplyStatus(originId);
				}
				else	deleteChecker	=	"false";
			}
			else {
				deleteChecker	=	"notyou";
			}
		}
		
		
		
		
		mav.addObject("deleteChecker", deleteChecker);
		System.out.println("______________________________________________________");
		return mav;
	}
	
}
