package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_post.dao.PostDAO;
import com.aka_user.dao.UserDAO;

@Controller
public class deleteUserdata {
	@Autowired
	private UserDAO dao ;
	
	@Autowired	
	private PostDAO postDao;
	
	@Autowired	
	private guestbookDAO guestbookDao;
	
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/deleteUserdata.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,@RequestParam(value="email", defaultValue="") String email) {
		System.out.println("_____________________________________________");
		System.out.println("deleteUserdata.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("mgr_account/deleteUserdata");
		boolean deleteChecker = false;
		System.out.println("email 		: " + email);
		
		if(email.equals("")) {
			//email이 안넘어왔으면 false를 리턴
			mav.addObject("deleteChecker", deleteChecker);
			return mav;
		}
		//######
		
		deleteChecker = dao.del_authData(email);
		deleteChecker = dao.del_loginData(email);
		deleteChecker = dao.del_userData(email);
		
		postDao.deletePostByWriterEmail(email);
		guestbookDao.deleteMsgByWriterEmail(email);
		guestbookDao.deleteReplyByWriterEmail(email);
		
		//######
		
		mav.addObject("deleteChecker", deleteChecker);
		System.out.println("_____________________________________________");
		return mav; 
	}
}
