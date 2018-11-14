package com.aka_guestbook.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_guestbook.domain.GB_Admin_MsgCommand;
import com.aka_guestbook.domain.GB_GuestAndAdmin;
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
	public int insertAdminMsg(GB_Admin_MsgCommand adminMsg) {
		// TODO Auto-generated method stub
		int insertRes = getSqlSession().insert("insertAdminMsg", adminMsg);
		System.out.println("insertRes : "+insertRes);
		System.out.println("adminMsg.gb_id : "+adminMsg.getGb_id());
		if(insertRes != 0) {
			return adminMsg.getGb_id();
		}
		else return -666;
	}

	@Override
	public List<GB_Guest_MsgCommand> getGuestMsgs(StartAndEnd sae) {
		// TODO Auto-generated method stub
		System.out.println("sae : "+sae.getStart());
		System.out.println("sae : "+sae.getEnd());
		return getSqlSession().selectList("getGuestMsgs", sae);
		
	}

	@Override
	public GB_Guest_MsgCommand getGuestMsgById(int gb_id) {
		// TODO Auto-generated method stub
		return (GB_Guest_MsgCommand)getSqlSession().selectOne("getGuestMsgById", gb_id);
	}
	
	@Override
	public GB_Admin_MsgCommand getAdminMsgById(int gb_id) {
		// TODO Auto-generated method stub
		return (GB_Admin_MsgCommand)getSqlSession().selectOne("getAdminMsgById", gb_id);
	}

	@Override
	public boolean updateGuestMsgRef(GB_GuestAndAdmin dataSet) {
		// TODO Auto-generated method stub
		int updateChecker = getSqlSession().update("updateGuestMsgRef", dataSet);
		
		if(updateChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public int getGuestMsgCount() {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("getGuestMsgCount");
	}

	@Override
	public boolean deleteMsgById(int gb_id) {
		// TODO Auto-generated method stub
		int deleteChecker = getSqlSession().delete("deleteMsgById", gb_id);
		
		if(deleteChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean deleteReplyById(int gb_id) {
		// TODO Auto-generated method stub
		int deleteChecker = getSqlSession().delete("deleteReplyById", gb_id);
		
		if(deleteChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean resetReplyStatus(int gb_id) {
		// TODO Auto-generated method stub
		int updateChecker = getSqlSession().delete("resetReplyStatus", gb_id);
		
		if(updateChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean deleteMsgByWriterEmail(String gb_writer_email) {
		// TODO Auto-generated method stub
		int deleteChecker = getSqlSession().delete("deleteMsgByWriterEmail", gb_writer_email);
		
		if(deleteChecker != 0) {
			return true;
		}
		else return false;
	}

	@Override
	public boolean deleteReplyByWriterEmail(String gb_writer_email) {
		// TODO Auto-generated method stub
		int deleteChecker = getSqlSession().delete("deleteReplyByWriterEmail", gb_writer_email);
		
		if(deleteChecker != 0) {
			return true;
		}
		else return false;
	}
	
	


	

	
	
}
