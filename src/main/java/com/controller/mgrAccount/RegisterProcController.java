package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;

import constSet.MainConst;

@Controller
public class RegisterProcController {
	@Autowired
	private UserDAO dao ;
	
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/registerProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,@RequestParam(value="email"    ,defaultValue=""  ) String user_email
            							 ,@RequestParam(value="password" ,defaultValue=""  ) String user_password
										 ,@RequestParam(value="nickname" ,defaultValue=""  ) String user_nickname
										 ,@RequestParam(value="img_id"   ,defaultValue="-1") int img_id){
		System.out.println("_____________________________________________");
		System.out.println("RegisterProcController.requestProcessor >>> 매서드 호출됨");
		System.out.println("RegisterProcController.requestProcessor >>> user_email    : "+user_email);
		System.out.println("RegisterProcController.requestProcessor >>> user_password    : "+user_password);
		System.out.println("RegisterProcController.requestProcessor >>> user_nickname : "+user_nickname);
		System.out.println("RegisterProcController.requestProcessor >>> img_id : "+img_id);
		
		UserCommand newUser = new UserCommand();
		newUser.setUser_email(user_email);
		newUser.setUser_password(user_password);
		newUser.setUser_nickname(user_nickname);
		if(img_id != -1) {//이미지 등록된 경우
			newUser.setImg_id(img_id); 
		}
		else {//이미지 등록 안된 경우
			newUser.setImg_id(MainConst.IMG_DEFAULT_PROFILE_IMG);
		}
		
		System.out.println("RegisterProcController.requestProcessor >>> dao 접근 전");
		boolean insertChecker = dao.insertUserData(newUser);
		System.out.println("RegisterProcController.requestProcessor >>> dao 접근 후");
		
		ModelAndView mav = new ModelAndView("mgr_account/registerProc");
		System.out.println("RegisterProcController.requestProcessor >>> mav view : "+mav.getViewName());
		mav.addObject("insertChecker",insertChecker);
		System.out.println("_____________________________________________");
		return mav; 
	}

}
