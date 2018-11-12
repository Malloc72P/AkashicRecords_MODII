package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;
import com.aka_user.dao.UserDAO;
import com.util.SessionMapMgr;

@Controller
public class DeleteContentController {
	
	@Autowired
	private PostDAO dao;
	
	@Autowired
	private UserDAO	userDao;
	
	@RequestMapping("/hello/deleteContent.do")
	public ModelAndView requestProcessor(
											HttpServletRequest 		request
											,HttpServletResponse 	response
											,@RequestParam(value="ssnId", 	defaultValue="") String ssnId
											,@RequestParam(value="post_id", defaultValue="") String	post_id
										) {
		System.out.println("____________________________________________________");
		System.out.println("DeleteContentController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/deleteContent");
		response.setHeader("Access-Control-Allow-Origin","*");
		HttpSession session = null;
		String deleteChecker	=	"false";
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
		PostCommand delPost	=	dao.getPostById(post_id);
		if( delPost.getUser_email().equals(userEmail) || userDao.checkSuperUser(userEmail) ) {
			//지우려는 포스트의 작성자와 현재 삭제하려는 유저가 동일한 경우
			boolean tempData	=	dao.deletePostById(post_id);
			if( tempData ) {
				deleteChecker	=	"true";
			}
			else {
				deleteChecker	=	"false";
			}
		}
		else {
			//포스트 작성자와 포스트를 지우려는 유저가 동일하지 않은경우
			deleteChecker	=	"notYou";
		}
		
		
		mav.addObject("deleteChecker", deleteChecker);
		System.out.println("____________________________________________________");
		return mav;
	}
	
}
