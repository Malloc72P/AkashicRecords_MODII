package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;

@Controller
public class updateUserdata {
	@Autowired
	private UserDAO dao ;
	
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/updateUserData.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,@RequestParam(value="email"	 	, defaultValue="") String email
										 ,@RequestParam(value="imgid" 		, defaultValue="") int    imgid
										 ,@RequestParam(value="nickname"	, defaultValue="") String nickname
										 ,@RequestParam(value="validation" 	, defaultValue="") String validation
            							 ,@RequestParam(value="authority" 	, defaultValue="") String authority) {
		System.out.println("_____________________________________________");
		System.out.println("updateUserdata.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("mgr_account/updateUserdata");
		boolean updateChecker = false;
		
		System.out.println("email 		: " + email);
		System.out.println("imgid 		: " + imgid);
		System.out.println("nickname 	: " + nickname);
		System.out.println("validation	: " + validation);
		System.out.println("authority 	: " + authority);
		
		if(email.equals("")) {
			//email이 안넘어왔으면 false를 리턴
			mav.addObject("updateChecker", updateChecker);
			return mav;
		}
		UserCommand prevUsrData = dao.getUserDataByEmail(email);
		if(prevUsrData != null) {
			UserCommand newMetadata = new UserCommand();
			newMetadata.setUser_email(email);
			newMetadata.setImg_id(imgid);
			newMetadata.setUser_nickname(nickname);
			newMetadata.setValidation(validation);
			updateChecker	=	dao.mgr_updateUsrData(newMetadata);
		}
		else {//만약 해당 이메일을 가진 계정이 존재하지 않는다면
			mav.addObject("updateChecker", updateChecker);
			return mav;
		}
		
		mav.addObject("updateChecker", updateChecker);
		System.out.println("_____________________________________________");
		return mav; 
	}
}
