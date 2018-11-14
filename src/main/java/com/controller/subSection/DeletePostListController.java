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

import com.aka_image.dao.ImageDAO;
import com.aka_post.dao.PostDAO;
import com.aka_series.dao.SeriesDAO;
import com.aka_series.domain.SeriesCommand;
import com.aka_user.dao.UserDAO;
import com.util.FileUtil;
import com.util.SessionMapMgr;

@Controller
public class DeletePostListController {
	
	@Autowired
	private SeriesDAO dao;
	
	@Autowired
	private PostDAO postDao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/hello/deleteSeries.do")
	public ModelAndView requestProcessor( 
											HttpServletRequest 		request
											,HttpServletResponse 	response
											,@RequestParam(value="ssnId", defaultValue="") String ssnId
											,@RequestParam(value="seriesId", defaultValue="") int seriesId
											) {
		ModelAndView mav = new ModelAndView("subSection/deletePostList");
		System.out.println("______________________________________________________");
		System.out.println("DeletePostListController.requestProcessor >>> 메서드 호출됨"); 
		String	deleteChecker	=	"false";
		System.out.println("ssnId : "+ssnId);
		System.out.println("seriesId : "+seriesId);
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
					System.out.println("세션 인증완료. 관리자 권한 확인됨");
					System.out.println("________________________________________________________");
				}
			}
			
		}
		else {
			//ssnID를 못받은경우
			mav.addObject("deleteChecker","invalidSession");
			System.out.println("세선만료");
			return mav;
		}
		
		postDao.deletePostBySeriesId(seriesId);
		boolean sqlChecker	=	dao.deleteSeriesById(seriesId);
		if(sqlChecker) {
			deleteChecker	=	"true";
		}
		else	deleteChecker	=	"false";
		
		
		
		mav.addObject("deleteChecker", deleteChecker);
		System.out.println("______________________________________________________");
		return mav;
	}
	
}
