package com.controller.subSection;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;
import com.aka_post.domain.PostCommand;

@Controller
public class WritePostProcController {
	
	@Autowired
	private PostDAO dao;
	
	@RequestMapping("/hello/writePostProc.do")
	public ModelAndView requestProcessor(
										 HttpServletRequest request,
			                             @RequestParam("post_title")   String post_title    ,
										 @RequestParam("post_content") String post_content,
										 @RequestParam("series_id")    int    series_id 
										)
	{
		System.out.println("________________________________________________________");
		System.out.println("WritePostProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/writePostProc");
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
		int post_thumbnail			= 2;
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
			post_thumbnail = Integer.parseInt(uploadedImgList.get(0));
			System.out.println("post_thumbnail : "+post_thumbnail); 
		}
		
		
		
		System.out.println("***** TEXT PARSER *****");
		String post_summary = tagBody.text();
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
		post.setUser_email((String)request.getSession().getAttribute("email"));
		post.setSeries_id(series_id);
		post.setImg_id(2);
		post.setPost_img_list(post_img_list);
		post.setImg_id(post_thumbnail); 
		
		
		boolean insertChecker = true;
		insertChecker = dao.insertPost(post);
		
		
		mav.addObject("insertChecker",insertChecker);
		
		
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
