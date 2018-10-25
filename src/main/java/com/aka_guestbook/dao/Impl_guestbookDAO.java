package com.aka_guestbook.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_Guest_MsgCommand;
import com.aka_post.domain.StartAndEnd;

public class Impl_guestbookDAO extends SqlSessionDaoSupport implements guestbookDAO{

	@Override
	public boolean insertGuestMsg(GB_Guest_MsgCommand guestMsg) {
		// TODO Auto-generated method stub
		int insertChecker = getSqlSession().insert("insertGuestMsg", guestMsg);
		if(insertChecker != 0) {
			return true;
		}
		else return false;
	}
	
	@Override
	public boolean insertAdminMsg(GB_Admin_MsgCommand adminMsg) {
		// TODO Auto-generated method stub
		int insertChecker = getSqlSession().insert("insertAdminMsg", adminMsg);
		if(insertChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public List<GB_Guest_MsgCommand> getGuestMsgs(StartAndEnd sae) {
		// TODO Auto-generated method stub
		System.out.println("sae : "+sae.getStart());
		System.out.println("sae : "+sae.getEnd());
		return getSqlSession().selectList("getGuestMsgs", sae);
		
	}



	
	
}
