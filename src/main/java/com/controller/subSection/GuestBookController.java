package com.controller.subSection;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
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
		HashMap<Integer, String> guestMsg_timeSet = new HashMap<Integer, String>();
		for(GB_Guest_MsgCommand item : guestMsgList) {
			System.out.println("data : "+item.getGb_id());
			System.out.println("data : "+item.getGb_content());
			//2018-09-20, 목
			guestMsg_timeSet.put(item.getGb_id(), sdf.format(item.getGb_regdate())); 
		}
		System.out.println("_________________________________");
		
		mav.addObject("guestMsgList"	, guestMsgList);
		mav.addObject("guestMsg_timeSet", guestMsg_timeSet);
		System.out.println("_____________________________________________________");
		return mav;
	}
	
}
