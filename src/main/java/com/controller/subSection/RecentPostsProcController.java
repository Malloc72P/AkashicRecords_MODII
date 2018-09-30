package com.controller.subSection;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_post.dao.PostDAO;

@Controller
public class RecentPostsProcController {
	
	@Autowired
	private PostDAO dao;
	
	@RequestMapping("/hello/recentPosts.do")
	public ModelAndView requestProcessor(
											@RequestParam(value="pageNum", defaultValue="1") int currentPage ) {
		System.out.println("________________________________________________________");
		System.out.println("RecentPostsProcController.requestProcessor >>> 매서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/recentPosts");
		int pageSize = 5;
		int blockSize = 5;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		System.out.println("RecentPostsProcController.requestProcessor >>> currentPage : "+currentPage);
		
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;// 1*10=10,2*10=20,3*10=30(레코드갯수 X)
		int count = 0;// 총레코드수
		int number = 0;// beginPerPage->페이지별 시작하는 맨 처음에 나오는 게시물번호
		List articleList = null;// 화면에 출력할 레코드 전체
		
		
		
		int startCount = (currentPage - 1) * blockSize + 1;
		int endCount = currentPage * blockSize;
		
		System.out.println("RecentPostsProcController.requestProcessor >>> startRow : "+startRow);
		System.out.println("RecentPostsProcController.requestProcessor >>> endRow   : "+endRow);
		
		count = dao.getArticleCount();
		if (count > 0) {// 화면에 보여줄 레코드가 한개라도 존재한다면
			articleList = dao.getArticles(startCount, endCount);// 10개씩 (endRow X)
		}
		else {
			articleList = Collections.EMPTY_LIST;//해당 리스트가 비어있다는 것을 의미하는 상수
		}
		System.out.println("RecentPostsProcController.requestProcessor >>> count : "+count);
		number = count - (currentPage - 1) * pageSize;
		System.out.println("RecentPostsProcController.requestProcessor >>> 페이지 별 number : "+number);
		
		//_____________________________________________________________
	    //페이징처리
		int startPage = 0;
        int endPage   = 0;//1+10-1=10
        int pageCount = 0;
	    if(count > 0){//레코드가 한개이상 존재한다면
	    	//1.총페이지수 구하기
	    	//                    122/10=12.2+1.0=13.2=13
	    	pageCount=count/pageSize+(count%pageSize==0?0:1);
	        System.out.println("pageCount=>"+pageCount);
	        //2.시작페이지,끝페이지 
	        //                         1-((1-1)%10)
	        startPage=currentPage-((currentPage-1)%blockSize);
	        endPage=startPage+blockSize-1;//1+10-1=10
	        System.out.println("startPage="+startPage+",endPage="+endPage);
	        //블럭별로 구분해서 링크걸어서 출력
	        if(endPage > pageCount) endPage=pageCount;//마지막페이지=총페이지수
	        //3-1) 이전블럭(11페이지 이상)->if(11 > 10)
	    }
	    //_____________________________________________________________
	    //이렇게 처리 과정을 수행하고, 뷰에서 필요로 하는 변수값들을 뷰와 공유해야 한다.
		//이는 request객체를 이용해서 메모리에 데이터를 올리는 방식으로 공유한다.
		//뷰에서는 여러 방법으로 메모리에 접근해서 데이터를 가져오면 된다.
		//ex) EL태그
	    
	    mav.addObject("count"       , count       );
		mav.addObject("articleList" , articleList );
		mav.addObject("number"      , number      );
		mav.addObject("currentPage" , currentPage );
		mav.addObject("sdf"         , sdf         );
		mav.addObject("pageSize"    , pageSize    );
		mav.addObject("blockSize"   , blockSize   );
		mav.addObject("startPage"   , startPage   );
		mav.addObject("endPage"     , endPage     );
		mav.addObject("pageCount"   , pageCount   );
		
		System.out.println("________________________________________________________");
		return mav;
	}
	
}
