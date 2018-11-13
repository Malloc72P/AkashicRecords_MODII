package com.controller.subSection;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_image.dao.ImageDAO;
import com.aka_post.dao.PostDAO;
import com.aka_series.dao.SeriesDAO;
import com.aka_series.domain.SeriesCommand;
import com.aka_user.dao.UserDAO;
import com.util.FileUtil;
import com.util.SessionMapMgr;

@Controller
public class DeleteGbMsgController {
	
	@Autowired
	private guestbookDAO dao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/hello/deleteMsg.do")
	public ModelAndView requestProcessor( 
											HttpServletRequest 		request
											,HttpServletResponse 	response
											,@RequestParam(value="ssnId", defaultValue="") String ssnId
											,@RequestParam(value="msgId", defaultValue="") int msgId
											) {
		ModelAndView mav = new ModelAndView("subSection/deleteGbMsg");
		System.out.println("______________________________________________________");
		System.out.println("DeleteGbMsgController.requestProcessor >>> 메서드 호출됨"); 
		String	deleteChecker	=	"false";
		System.out.println("ssnId : "+ssnId);
		System.out.println("msgId : "+msgId);
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
		GB_Guest_MsgCommand	msgObj	=	dao.getGuestMsgById(msgId);
		if( msgObj.getGb_writer_email().equals(userEmail) || userDao.checkSuperUser(userEmail) ) {
			boolean sqlChecker	=	dao.deleteMsgById(msgId);
			if(sqlChecker) {
				deleteChecker	=	"true";
			}
			else	deleteChecker	=	"false";
		}
		else {
			deleteChecker	=	"notyou";
		}
		
		
		
		mav.addObject("deleteChecker", deleteChecker);
		System.out.println("______________________________________________________");
		return mav;
	}
	
}
