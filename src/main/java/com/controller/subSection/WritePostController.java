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
import com.util.SessionMapMgr;

@Controller
public class WritePostController {
	@Autowired
	private UserDAO	userDao;
	
	@RequestMapping("/hello/writePost.do")
	public ModelAndView requestProcessor(
										 HttpServletRequest 			request,
										 HttpServletResponse			response,
										 @RequestParam( value="ssnId", defaultValue="" )	String ssnId 
										)
	{
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePostProc");
		
		response.setHeader("Access-Control-Allow-Origin","*");
		HttpSession	session	=	null;
		if( !ssnId.equals("") ) {
			//ssnID를 받은 경우
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
			if( session == null ) {
				//세션이 서버에서 이미 만료된 경우
				mav.addObject("insertChecker","invalidSession");
				System.out.println("세선만료");
				return mav;
			}
			else {
				//세션이 사용 가능한 경우
				if( !userDao.checkSuperUser( (String)session.getAttribute("email") ) ) {
					//권한이 없는 경우
					mav.addObject("insertChecker","lowAuthorize");
					System.out.println("권한이 없는 경우");
					return mav;	
				}
				else {
					//권한이 충분한 경우.
					//이 조건문까지 진입해야만 session이 체크되었다는 의미임.
					mav.addObject("insertChecker","validSession");
					System.out.println("validSession");
					return mav;
				}
			}
			
		}
		else {
			//ssnID를 못받은경우
			mav.addObject("insertChecker","invalidSession");
			System.out.println("세선만료");
			return mav;
		}
	}
	
}
