package com.controller.subSection;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_post.domain.StartAndEnd;

@Controller
public class GuestBookController {
	
	@Autowired
	guestbookDAO dao;
	
	@RequestMapping("/hello/guestBook.do")
	public ModelAndView requestProcessor() {
		System.out.println("_____________________________________________________");
		System.out.println("GuestBookController.requestProcessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/guestBook");
		
		StartAndEnd sae = new StartAndEnd();
		sae.setStart(1);
		sae.setEnd(10);
		
		List<GB_Guest_MsgCommand> guestMsgList = dao.getGuestMsgs(sae);
		
		System.out.println("_________________________________");
		System.out.println("gusetMsgList .size : "+ guestMsgList.size()); 
		System.out.println("gusetMsgList 출력중...");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		HashMap<Integer, String> 				guestMsg_timeSet	= new HashMap<Integer, String>();
		HashMap<Integer, String> 				adminMsg_timeSet	= new HashMap<Integer, String>();
		HashMap<Integer, GB_Admin_MsgCommand>	guestReplyMap		= new HashMap<Integer, GB_Admin_MsgCommand>();
		
		for(GB_Guest_MsgCommand item : guestMsgList) {
			System.out.println("data : "+item.getGb_id());
			System.out.println("data : "+item.getGb_content());
			//2018-09-20, 목
			guestMsg_timeSet.put(item.getGb_id(), sdf.format(item.getGb_regdate())); 
			
			//답글이 달려있는지 확인하고, 있으면 맵에 저장
			if(item.getGb_from_admin_id() != -1) {
				GB_Admin_MsgCommand replyMsg = dao.getAdminMsgById(item.getGb_from_admin_id());
				guestReplyMap.put(item.getGb_id(), replyMsg);
				adminMsg_timeSet.put(replyMsg.getGb_id(), sdf.format(replyMsg.getGb_regdate())); 
				//guestReplyMap.get( item.getGb_id() ).getGb_writer_email()
			}
			
		}
		System.out.println("_________________________________");
		
		mav.addObject("guestMsgList"	, guestMsgList);
		mav.addObject("guestMsg_timeSet", guestMsg_timeSet);
		mav.addObject("adminMsg_timeSet", adminMsg_timeSet);
		mav.addObject("guestReplyMap", guestReplyMap);
		System.out.println("_____________________________________________________");
		return mav;
	}
	
}
