package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_series.dao.SeriesDAO;

@Controller
public class WriteSeriesProcController {
	
	@Autowired
	private SeriesDAO dao;
	
	@RequestMapping("/hello/writeSeriesProc.do")
	public ModelAndView requestProcessor(HttpServletRequest request) {
		System.out.println("_______________________________________________________");
		System.out.println("WriteSeriesProcController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writeSeriesProc");
		
		String seriesTitle = request.getParameter("seriesTitle");
		
		boolean	insertChecker = false;
		insertChecker	=	dao.insertSeries(seriesTitle);
		
		mav.addObject("insertChecker",insertChecker);
		System.out.println("_______________________________________________________");
		return mav;
	}
	
}
