package com.controller.subSection;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_image.dao.ImageDAO;
import com.aka_series.dao.SeriesDAO;
import com.aka_series.domain.SeriesCommand;
import com.util.FileUtil;

@Controller
public class PostListController {
	
	@Autowired
	private SeriesDAO dao;
	
	@Autowired
	private ImageDAO imgDAO;
	
	@RequestMapping("/hello/postList.do")
	public ModelAndView requestProcessor( 
											HttpServletRequest 		request
											,HttpServletResponse 	response
											) {
		ModelAndView mav = new ModelAndView("subSection/postList");
		response.setHeader("Access-Control-Allow-Origin","*");
		List<SeriesCommand> seriesList = dao.getSeriesList();
		
		HashMap<Integer, String> imgMap = new HashMap<Integer, String>();
		for(SeriesCommand item : seriesList) {
			System.out.println("______________________________");
			System.out.println("testing : "+ item.getSeries_id());
			System.out.println("testing : "+ item.getSeries_Title());
			System.out.println("testing : "+ item.getSeries_regdate());
			System.out.println("testing : "+ item.getSeries_viewcount());
			System.out.println("testing : "+ item.getSeries_postcount());
			System.out.println("testing : "+ item.getImg_id());
			System.out.println("testing : "+ imgDAO.getImgUrlById(item.getImg_id()));
			String imgPath	=	imgDAO.getImgUrlById(item.getImg_id());
			//String imgUrl	=	FileUtil.makeImgUrl(request, imgPath);
			//System.out.println("testing : "+ imgUrl);
			imgMap.put(item.getImg_id(), FileUtil.makeImgUrl(request, imgPath));
			System.out.println("______________________________");
		}
		
		mav.addObject("seriesList",	seriesList);
		mav.addObject("imgMap",		imgMap);
		mav.addObject("seriesCount",seriesList.size());
		
		return mav;
	}
	
}
