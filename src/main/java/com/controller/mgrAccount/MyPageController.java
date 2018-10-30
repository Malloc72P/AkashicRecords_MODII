package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_image.dao.ImageDAO;
import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;
import com.util.FileUtil;

@Controller
public class MyPageController {

	@Autowired
	UserDAO dao;
	
	@Autowired
	ImageDAO imageDao;
	
	@RequestMapping("/hello/myPage.do")
	public ModelAndView requestProcessor( HttpServletRequest request,
			                              @RequestParam("user_password") String user_password ) {
		System.out.println("________________________________________________________");
		System.out.println("pwCheckProcController.requestProcessor >>> 매서드 호출됨");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView("mgr_account/myPageMetadata");
		String user_email   = "";
		boolean validator	= 	false;
		String activation	=	"false";
		UserMetadataCommand userMetaData = null;
		
		if(session.getAttribute("email") != null) {
			user_email = (String)session.getAttribute("email");
			UserCommand user = dao.getUserDataByEmail(user_email);
			
			if(user.getUser_password().equals(user_password)) {
				userMetaData = dao.getUserMetaDataByEmail(user_email);
				validator 	= 	true;
				activation	=	userMetaData.getValidation();
			}
			else {
				validator = false;
			}
		}
		else {
			validator = false;
		}
		System.out.println("MyPageController.requestProcessor >>> validator : "+validator);
		
		
		mav.addObject( "validator",	 validator );
		mav.addObject( "activation", activation );
		mav.addObject( "email", userMetaData.getUser_email() );
		mav.addObject( "nickname", userMetaData.getUser_nickname() );
		mav.addObject( "img_id", userMetaData.getImg_id() );
		String imgRealPath = FileUtil.makeImgUrl(request, imageDao.getImgUrlById( userMetaData.getImg_id() ));
		mav.addObject( "img_url", imgRealPath);
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
