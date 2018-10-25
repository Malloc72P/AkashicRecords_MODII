package com.aka_guestbook.dao;

import java.util.List;

import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_post.domain.StartAndEnd;

public interface guestbookDAO {
	public boolean insertGuestMsg(GB_Guest_MsgCommand guestMsg);
	public boolean insertAdminMsg(GB_Admin_MsgCommand adminMsg);
	public List<GB_Guest_MsgCommand> getGuestMsgs(StartAndEnd sae);
	
}
