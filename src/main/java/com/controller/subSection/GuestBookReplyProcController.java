package com.controller.subSection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aka_guestbook.dao.guestbookDAO;
import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_GuestAndAdmin;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;

@Controller
public class GuestBookReplyProcController {

	@Autowired
	private guestbookDAO dao;
	
	@RequestMapping("/hello/guestBookReplyProc.do")
	public ModelAndView requestProccessor( 	@RequestParam("gbReplyMsg") String msg ,
			 								@RequestParam("gbReplyMsg_id") int msg_id,
			 								HttpServletRequest request
			 ) {
		System.out.println("___________________________________________________________");
		System.out.println("GuestBookReplyProcController.requestProccessor >>> 메서드 호출됨");
		ModelAndView mav = new ModelAndView("subSection/guestBookReplyProc");
		
		boolean insertChecker = false;
		System.out.println("msg : "+msg);
		System.out.println("msg_id : "+msg_id); 
		
		GB_Admin_MsgCommand adminMsg = new GB_Admin_MsgCommand();
		adminMsg.setGb_writer_email((String)request.getSession().getAttribute("email"));
		adminMsg.setGb_content(msg);
		adminMsg.setGb_to_guest_id(msg_id);
		//STEP.1 : 해당 방명록에 답글이 이미 달려있는지 검사.
		//달려있으면 답글 못달게 막음.
		GB_Guest_MsgCommand guestMsg = dao.getGuestMsgById(msg_id);
		
		if(guestMsg.getGb_from_admin_id() != -1) {
			//만약, 방명록에 답글이 이미 달려있다면 -1과 같지 않음
			//그런 경우, 바로 mav를 리턴해서 명령처리메서드 종료
			System.out.println("이미 답글이 달려있습니다");
			return mav;
		}
		
		//STEP.2 데이터 삽입 실시
		int adminMsgId = dao.insertAdminMsg(adminMsg);
		
		if(adminMsgId != 666) {//666이 아니라면, 성공적으로 값을 입력한 것이다.
			//STEP.3 방명록의 답글참조컬럼에 답글의 ID를 업데이트 시켜줌
			GB_GuestAndAdmin dataSet = new GB_GuestAndAdmin();
			dataSet.setGb_guest_id(msg_id);
			dataSet.setGb_admin_id(adminMsgId);
			insertChecker = dao.updateGuestMsgRef(dataSet);
		}
		else {
			insertChecker = false;
		}
		
		
		
		mav.addObject("insertChecker", insertChecker);
		System.out.println("___________________________________________________________");
		return mav;
	}
	
}
