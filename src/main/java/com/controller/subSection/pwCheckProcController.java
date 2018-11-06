package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_user.dao.UserDAO;
import com.aka_user.domain.UserCommand;
import com.util.SessionMapMgr;

@Controller
public class pwCheckProcController {

	@Autowired
	UserDAO dao;
	
	@RequestMapping("/hello/pwCheckProc.do")
	public ModelAndView requestProcessor( HttpServletRequest request
										  ,HttpServletResponse response
										  ,@RequestParam(value="ssnId", defaultValue="") String ssnId
			                              ,@RequestParam("password") String user_password ) {
		System.out.println("________________________________________________________");
		System.out.println("pwCheckProcController.requestProcessor >>> 매서드 호출됨");
		
		ModelAndView mav = new ModelAndView("subSection/pwCheckProc");
		HttpSession session = request.getSession();
		
		if( !ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
		}
		else {
			mav.addObject( "validator",	 false );
			mav.addObject( "activation", false );
			return mav;
		}
		response.setHeader("Access-Control-Allow-Origin","*");
		String user_email   = "";
		boolean validator	= 	false;
		String activation	=	"false";
		
		if(session.getAttribute("email") != null) {
			user_email = (String)session.getAttribute("email");
			UserCommand user = dao.getUserDataByEmail(user_email);
			
			if(user.getUser_password().equals(user_password)) {
				validator 	= 	true;
				activation	=	dao.getUserMetaDataByEmail(user_email).getValidation();
			}
			else {
				validator = false;
			}
		}
		else {
			validator = false;
		}
		System.out.println("pwCheckProcController.requestProcessor >>> validator : "+validator);
		
		
		mav.addObject( "validator",	 validator );
		mav.addObject( "activation", activation );
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
