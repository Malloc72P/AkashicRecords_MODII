package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_series.dao.SeriesDAO;
import com.aka_user.dao.UserDAO;
import com.util.SessionMapMgr;

@Controller
public class WriteSeriesProcController {
	
	@Autowired
	private SeriesDAO dao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping("/hello/writeSeriesProc.do")
	public ModelAndView requestProcessor(
											HttpServletRequest 		request
											,HttpServletResponse	response
											,@RequestParam( value="ssnId", defaultValue="") String ssnId
											,@RequestParam( value="seriesTitle", defaultValue="") String seriesTitle
										) {
		System.out.println("_______________________________________________________");
		System.out.println("WriteSeriesProcController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writeSeriesProc");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		HttpSession	session	=	null;
		if( !ssnId.equals("") ) {
			session	=	SessionMapMgr.getInstance().getSessionMap().get(ssnId);
			if( session == null ) {
				mav.addObject("insertChecker","invalidSession");
				return mav;
			}
			else {
				if( !userDao.checkSuperUser( (String)session.getAttribute("email") ) ) {
					mav.addObject("insertChecker","lowAuthorize");
					return mav;	
				}
			}
			
		}
		else {
			mav.addObject("insertChecker","invalidSession");
			return mav;
		}
		if(seriesTitle.equals("")) {
			mav.addObject("insertChecker","noArgument");
			return mav;
		}
		
		
		boolean	insertChecker = false;
		insertChecker	=	dao.insertSeries(seriesTitle);
		
		mav.addObject("insertChecker",insertChecker);
		System.out.println("_______________________________________________________");
		return mav;
	}
	
}
