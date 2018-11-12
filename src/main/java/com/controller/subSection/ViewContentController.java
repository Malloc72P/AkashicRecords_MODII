package com.controller.subSection;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;
import com.aka_series.dao.SeriesDAO;

@Controller
public class ViewContentController {
	
	@Autowired
	private PostDAO dao;
	
	@Autowired
	private SeriesDAO seriesDao;
	
	@RequestMapping("/hello/viewContent.do")
	public ModelAndView requestProcessor(
											HttpServletRequest 		request
											,HttpServletResponse 	response
										) {
		System.out.println("____________________________________________________");
		System.out.println("ViewContentController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/viewContent");
		response.setHeader("Access-Control-Allow-Origin","*");
		
		int post_id = Integer.parseInt( request.getParameter("post_id") );
		PostCommand post 	= 	dao.getPostById(request.getParameter("post_id"));
		
		if(post == null) {
			mav.addObject("errorChecker","noPost");
			return mav;
		}
		else if( post.getUser_email() == null ) {
			mav.addObject("errorChecker","noPost");
			return mav;
		}
		else if( post.getUser_email().equals("") ) {
			mav.addObject("errorChecker","noPost");
			return mav;
		}
		
		String 		writer	=	dao.getUserNicknameByPostEmail(post.getUser_email());
		String		series	=	dao.getSeriesTitleByPostSeriesId(post.getSeries_id());
		
		dao.viewCountIncrementer( post_id );
		int series_id = dao.getSeriesIdByPostId(post_id);
		seriesDao.updateSeriesViewcount( series_id );
		
		System.out.println("writer : "+writer);
		System.out.println("series : "+series);
		
		mav.addObject("post",	post  );
		mav.addObject("writer",	writer);
		mav.addObject("series",	series);
		
		System.out.println("____________________________________________________");
		return mav;
	}
	
}
