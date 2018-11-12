package com.controller.subSection;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_image.dao.ImageDAO;
import com.aka_image.domain.ImageCommand;
import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;
import com.aka_series.dao.SeriesDAO;
import com.aka_series.domain.SeriesCommand;
import com.aka_user.dao.UserDAO;
import com.util.FileUtil;
import com.util.SessionMapMgr;

import constSet.MainConst;

@Controller
public class WritePostProcController {
	
	@Autowired
	private PostDAO dao;
	
	@Autowired
	private ImageDAO imgDao;
	
	@Autowired
	private SeriesDAO seriesDao;
	
	@Autowired
	private UserDAO	userDao;
	
	
	@RequestMapping(value="/hello/writePostProc.do")
	public ModelAndView requestProcessor(
										 HttpServletRequest 			request,
										 HttpServletResponse			response
										)
	{
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePostProc");

//		if( post_title.equals("") || post_content.equals("") ) {
//			mav.addObject("insertChecker","noArgument");
//			return mav;
//		}
//		 @RequestParam("ssnId")			String ssnId,
//        @RequestParam("post_title")   	String post_title    ,
//		 @RequestParam("post_content") 	String post_content,
//		 @RequestParam("series_id")    	int    series_id 
		String 	ssnId 			=	request.getParameter("ssnId");
        String 	post_title   	=	request.getParameter("post_title");
		String 	post_content 	=	request.getParameter("post_content");
		String 	str_series_id 	=	request.getParameter("series_id");
		int		series_id		=	-1;
		System.out.println("prev param check");
		System.out.println("WritePostProcController.requestProcessor >>> ssnId		  : "+ssnId);
		System.out.println("WritePostProcController.requestProcessor >>> post_title   : "+post_title);
		System.out.println("WritePostProcController.requestProcessor >>> post_content : "+post_content);
		System.out.println("WritePostProcController.requestProcessor >>> series_id    : "+series_id);
		
		if(str_series_id != null) {
			if(!str_series_id.equals("")) {
				series_id	=	Integer.parseInt(str_series_id);
			}
		}
		
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
		System.out.println("last param check");
		System.out.println("WritePostProcController.requestProcessor >>> ssnId		  : "+ssnId);
		System.out.println("WritePostProcController.requestProcessor >>> post_title   : "+post_title);
		System.out.println("WritePostProcController.requestProcessor >>> post_content : "+post_content);
		System.out.println("WritePostProcController.requestProcessor >>> series_id    : "+series_id);
		
		Document contentDOC = Jsoup.parse(post_content); 
		Element tagBody = contentDOC.body();
		
		
		System.out.println("***** HTML BODY *****");
		System.out.println(tagBody);
		
		StringBuffer imgIdList = new StringBuffer("");
		
		System.out.println("***** IMAGE PARSER *****");
		String uploadedImgDectector = "aka_imgID_";		
		int post_thumbnail_id		= 2;
		boolean isThereAnyUploadedImg = false;
		for(Element el : tagBody.select("img")) {
			
			if(el.attr("title").indexOf(uploadedImgDectector) > -1) {//문자열 찾은경우 >>> 업로드 된 이미지인 경우
				isThereAnyUploadedImg = true;
				System.out.println("*******************");	
				System.out.println("업로드 된 이미지 >>> el : "+el);
				System.out.println("index : "+el.attr("title").indexOf(uploadedImgDectector));
				System.out.println("img_id : "+el.attr("title").substring(10));
				imgIdList.append( el.attr("title").substring(10) );
				imgIdList.append(',');
				System.out.println("*******************");
			}
			else {
				System.out.println("외부 사이트의 이미지 >>> el : "+el);
			}
			
		}
		if(isThereAnyUploadedImg) {//만약 업로드 된 이미지가 있다면 해당 조건문이 발동한다
			/*해당 블록에선 이미지리스트로 만들어진 문자열을 파싱하여 ArrayList에 업로드 된 이미지의 id를 넣는작업을 하고,
			 * 리스트의 첫번째 노드의 값을 포스트의 썸네일로 저장하는 기능을 수행한다.
			 * 
			 * */
			System.out.println("WritePostProcController.requestProcessor >>> imgIdList : "+imgIdList);
			StringTokenizer tokenizer = new StringTokenizer(imgIdList.toString(), ",");
			List<String> uploadedImgList = new ArrayList<String>();
			
			while( tokenizer.hasMoreTokens() ) {
				String temp = tokenizer.nextToken();
				System.out.println("token >>> img_id : "+temp);
				uploadedImgList.add(temp);
			}
			
			post_thumbnail_id = Integer.parseInt(uploadedImgList.get(0));
			
			ServletContext application = request.getServletContext();
			
			String absol_path			=	application.getRealPath("").replace("\\", "/");
			//String img_Dir_path			=	absol_path+MainConst.IMG_ROOT_PATH+MainConst.IMG_POST_PATH;
			String img_Dir_path			=	absol_path+"/"+MainConst.IMG_ROOT_PATH+MainConst.IMG_POST_PATH;
			String post_thumbnail_name	=	imgDao.getImgNameById(post_thumbnail_id);
			
			
			int pos = post_thumbnail_name.lastIndexOf( "." );
			String post_thumbnail_ext	=	post_thumbnail_name.substring( pos + 1 );
			ImageCommand newThumbImg	=	null;
			try {
				newThumbImg	=	FileUtil.makeThumbnail(img_Dir_path, post_thumbnail_name, post_thumbnail_ext);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("[썸네일] >>> newThumbImg.getImg_name()	: "+newThumbImg.getImg_name());
			System.out.println("[썸네일] >>> newThumbImg.getImg_url		: "+newThumbImg.getImg_url());
			System.out.println("[썸네일] >>> newThumbImg.getImg_size		: "+newThumbImg.getImg_size());
			System.out.println("[썸네일] >>> newThumbImg.getImg_ref_type	: "+newThumbImg.getImg_ref_type());
			
			imgDao.insertImage(newThumbImg);
			
			post_thumbnail_id = imgDao.getImgIdByName(newThumbImg.getImg_name());
			System.out.println("post_thumbnail : "+post_thumbnail_id); 
		}
		
		
		
		System.out.println("***** TEXT PARSER *****");
		String post_summary = tagBody.text().trim();
		
		post_summary	=	post_summary.replaceAll("(^\\p{Z}+|\\p{Z}+$)", "");
		
		
		int post_summary_length = post_summary.getBytes().length;
		
		System.out.println("text : "+post_summary );
		System.out.println("TAG_BODY_LENGTH : "+tagBody.toString().length());
		System.out.println("post_summary_LENGTH : "+post_summary_length);
		
		if(post_summary_length > 1000) {
			StringBuffer buff = new StringBuffer( post_summary.substring(0, 500) ); 
			post_summary  = new String( buff.toString() );
					
			System.out.println("modified post_summary_LENGTH : "+post_summary.getBytes().length);
		}
				
		
		
		
		String post_img_list = imgIdList.toString();
		if( !post_img_list.equals("")) {
			System.err.println("포스트에 이미지 있음!");
		}
		else {
			System.err.println();
		}
		
		
		
		PostCommand post = new PostCommand();
		
		post.setPost_id(dao.getNewPostNum());
		post.setPost_title(post_title);
		post.setPost_content(post_content);
		post.setPost_summary(post_summary);
		post.setPost_viewcount(0);
		post.setUser_email((String)session.getAttribute("email"));
		post.setSeries_id(series_id);
		post.setPost_img_list(post_img_list);
		post.setImg_id(post_thumbnail_id); 
		
		//시리즈 메타데이터 수정하는 코드
		SeriesCommand seriesData = new SeriesCommand();
		seriesData.setSeries_id(series_id);
		seriesData.setImg_id(post_thumbnail_id);
		
		boolean insertChecker = false;
		insertChecker = dao.insertPost(post);
		if(post_thumbnail_id == 2) {//이미지가 없을때
			seriesDao.updateSeriesPostcount(seriesData.getSeries_id());
		}
		else {
			seriesDao.updateSeriesAndImg(seriesData);
		}
		
		
		
		mav.addObject("insertChecker",insertChecker);
		
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
