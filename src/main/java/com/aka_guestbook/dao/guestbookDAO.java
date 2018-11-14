package com.aka_guestbook.dao;

import java.util.List;

import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_GuestAndAdmin;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_post.domain.StartAndEnd;

public interface guestbookDAO {
	public boolean insertGuestMsg(GB_Guest_MsgCommand guestMsg);
	public int insertAdminMsg(GB_Admin_MsgCommand adminMsg);
	
	public GB_Guest_MsgCommand getGuestMsgById( int gb_id );
	public GB_Admin_MsgCommand getAdminMsgById( int gb_id );
	
	public boolean updateGuestMsgRef(GB_GuestAndAdmin dataSet);
	
	public List<GB_Guest_MsgCommand> getGuestMsgs(StartAndEnd sae);
	
	public int getGuestMsgCount();
	
	public boolean	deleteMsgById( int gb_id );
	public boolean	deleteReplyById( int gb_id );
	
	public boolean	resetReplyStatus(int gb_id);
	
	public boolean	deleteMsgByWriterEmail(String gb_writer_email);
	public boolean	deleteReplyByWriterEmail(String gb_writer_email);
	
}
