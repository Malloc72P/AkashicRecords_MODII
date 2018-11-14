package com.controller.mgrAccount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import	com.util.SessionMapMgr;

@Controller
public class LoginProcController {
	@Autowired
	private UserDAO dao ;
	
	@Autowired
	private ImageDAO imgDao;
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello/loginProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										 ,HttpServletResponse response
										 ,@RequestParam(value="email" , defaultValue="") String user_email
            							 ,@RequestParam(value="password" , defaultValue="") String user_password) {
		System.out.println("_____________________________________________");
		System.out.println("loginProcController.requestProcessor >>> 매서드 호출됨");
		
		response.setHeader("Access-Control-Allow-Origin","*");
		
		ModelAndView mav = new ModelAndView("mgr_account/loginProc");
		
		HttpSession	session		=	request.getSession();
		String		sessionId	=	session.getId();
		
		System.out.println("######################################################");	 
		System.out.println("sessionId : "+sessionId);
		SessionMapMgr	sessionMgr	=	SessionMapMgr.getInstance();
		sessionMgr.getSessionMap().put(sessionId, session);
		
		HttpSession newSession = sessionMgr.getSessionMap().get(sessionId);
		System.out.println("newSession : "+newSession.getId());
		
		System.out.println("######################################################");
		
		
		System.out.println("request ip : "+request.getRemoteAddr());
		System.out.println("sessionId : "+sessionId);
		
		System.out.println("loginProcController.requestProcessor >>> user_email : "+user_email);
		System.out.println("loginProcController.requestProcessor >>> user_password : "+user_password);
		
		System.out.println("loginProcController.requestProcessor >>> dao접근 전");
		UserMetadataCommand userdata = dao.getUserMetaDataByEmail(user_email);
		System.out.println("loginProcController.requestProcessor >>> dao접근 후");
		System.out.println("loginProcController.requestProcessor >>> dao.getEmail : "+userdata.getUser_email());
		
		
		
		boolean loginChecker 	= 	false;
		boolean superChecker	=	false;
		String	imgUrl			=	"";
		if(userdata.getUser_password().equals(user_password)) {
			System.out.println("loginProcController.requestProcessor >>> 패스워드 일치");
			loginChecker = true;
			request.getSession().setAttribute("loginChecker", loginChecker);
			request.getSession().setAttribute("email"       , user_email);
			System.out.println("loginProcController.requestProcessor >>> 세션 저장 성공");
			
			superChecker 	= 	dao.checkSuperUser(user_email);
			imgUrl			=	FileUtil.makeImgUrl(request, imgDao.getImgUrlById( userdata.getImg_id() ));
			
		}
		
		//######
		mav.addObject("loginChecker",	loginChecker);
		mav.addObject("user_email",		user_email);
		mav.addObject("ssnId",			newSession.getId());
		mav.addObject("imgUrl",			imgUrl);
		//######
		System.out.println("user_email		: "+user_email);
		System.out.println("loginChecker	: "+loginChecker);
		System.out.println("superChecker	: "+superChecker);
		System.out.println("_____________________________________________");
		
		mav.addObject("superChecker",superChecker);
		System.out.println("loginProcController.requestProcessor >>> mav view : "+mav.getViewName());
		return mav; 
	}
}
