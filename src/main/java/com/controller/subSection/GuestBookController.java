package com.controller.subSection;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_image.dao.ImageDAO;
import com.aka_post.domain.StartAndEnd;
import com.aka_user.dao.UserDAO;

@Controller
public class GuestBookController {
	
	@Autowired
	guestbookDAO dao;
	
	@Autowired
	ImageDAO imgDao;
	
	@Autowired 
	UserDAO userDao;
	
	
	
	@RequestMapping("/hello/guestBook.do")
	public ModelAndView requestProcessor( @RequestParam(value="pageNum", defaultValue="1") int currentPage ) {
		System.out.println("_____________________________________________________");
		System.out.println("GuestBookController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/guestBook");
		
		
		int pageSize 	=	10;
		int blockSize	=	10;
		
		
		int	count		=	0;
		//startCount와 endCount로 RowNum을 제한하여 메세지를 가져옴
		int startCount 	= 	(currentPage - 1) * blockSize +1;
		int endCount	=	currentPage * blockSize;
		
		StartAndEnd sae = new StartAndEnd();
		sae.setStart(startCount);
		sae.setEnd(endCount);
		
		List<GB_Guest_MsgCommand> guestMsgList = dao.getGuestMsgs(sae);
		
		System.out.println("_________________________________");
		System.out.println("gusetMsgList .size : "+ guestMsgList.size()); 
		System.out.println("gusetMsgList 출력중...");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		HashMap<Integer, String> 				guestMsg_timeSet	= new HashMap<Integer, String>();
		HashMap<Integer, String> 				adminMsg_timeSet	= new HashMap<Integer, String>();
		HashMap<Integer, GB_Admin_MsgCommand>	guestReplyMap		= new HashMap<Integer, GB_Admin_MsgCommand>();
		
		HashMap<String, String>	adminProfImgMap	= new HashMap<String, String>();
		HashMap<String, String>	guestProfImgMap	= new HashMap<String, String>();
		adminProfImgMap.clear();
		guestProfImgMap.clear();
		System.out.println(adminProfImgMap.containsKey("dase1102@gmail.com"));
		count	=	dao.getGuestMsgCount();
		
		for(GB_Guest_MsgCommand item : guestMsgList) {
			System.out.println("data : "+item.getGb_id());
			System.out.println("data : "+item.getGb_content());
			//2018-09-20, 목
			guestMsg_timeSet.put(item.getGb_id(), sdf.format(item.getGb_regdate()));
			
			String guestEmail = item.getGb_writer_email();
			System.out.println("######");
			System.out.println("item : "+item.getGb_content());
			if( !adminProfImgMap.containsKey( guestEmail ) ) {
				System.out.println("프로필 이미지 저장(guest)");
				int img_id = userDao.getUserMetaDataByEmail(guestEmail).getImg_id();
				System.out.println("img_id : "+img_id);
				String imgPath = imgDao.getImgUrlById( img_id );
				System.out.println("imgPath : "+imgPath);
				guestProfImgMap.put( guestEmail, imgPath);
			}
			
			System.out.println("|||||||||||||"); 
			//답글이 달려있는지 확인하고, 있으면 맵에 저장
			if(item.getGb_from_admin_id() != -1) {
				System.out.println("답글 확인됨");
				GB_Admin_MsgCommand replyMsg = dao.getAdminMsgById(item.getGb_from_admin_id());
				guestReplyMap.put(item.getGb_id(), replyMsg);
				adminMsg_timeSet.put(replyMsg.getGb_id(), sdf.format(replyMsg.getGb_regdate())); 
				//guestReplyMap.get( item.getGb_id() ).getGb_writer_email()
				String adminEmail = dao.getAdminMsgById(item.getGb_from_admin_id()).getGb_writer_email();
				if( !adminProfImgMap.containsKey( adminEmail ) ) {
					System.out.println("프로필 이미지 저장(admin)");
					int img_id = userDao.getUserMetaDataByEmail(adminEmail).getImg_id();
					System.out.println("img_id : "+img_id);
					String imgPath = imgDao.getImgUrlById( img_id );
					System.out.println("imgPath : "+imgPath);
					adminProfImgMap.put( adminEmail, imgPath );
				}
			}
			System.out.println("######");
			
		}//for
		System.out.println("final testing : "+ guestProfImgMap.get("dase1102@gmail.com")  );
		
		System.out.println("_________________________________");
		
		mav.addObject("guestMsgList"	, guestMsgList);
		mav.addObject("guestMsg_timeSet", guestMsg_timeSet);
		mav.addObject("adminMsg_timeSet", adminMsg_timeSet);
		mav.addObject("guestReplyMap", guestReplyMap);
		mav.addObject("adminProfImgMap", adminProfImgMap);
		mav.addObject("guestProfImgMap", guestProfImgMap);
		System.out.println("_____________________________________________________");
		
		int pageCount = 0;//pageCount는 총 페이지의 갯수이다. 이걸 이용해서 메세지 더 가져오기 버튼을 활성화할지 비활성화할지를 결정한다.
		
		if(count > 0) {
			pageCount = count / pageSize + (count % pageSize == 0 ? 0:1);
			
		}
		else {
			guestMsgList = Collections.EMPTY_LIST;
		}
		mav.addObject("count", 			count);
		mav.addObject("pageCount", 		pageCount);
		mav.addObject("currentPage", 	currentPage);
		
		
		/*
		 * currentPage, pageCount, count
		 * */
		return mav;
	}
	
}
