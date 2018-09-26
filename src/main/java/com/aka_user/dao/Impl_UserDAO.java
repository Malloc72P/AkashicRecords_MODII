package com.aka_user.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_user.domain.UserCommand;

import constSet.MainConst;

public class Impl_UserDAO extends SqlSessionDaoSupport implements UserDAO {

	public UserCommand getUserDataByEmail(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.getUserDataByEmail >>> 매서드 호출됨");
		UserCommand userdata = (UserCommand)getSqlSession().selectOne("getUserDataByEmail",user_email);
		
		if(userdata != null) {//검색성공한 경우
			System.out.println("Impl_UserDAO.getUserDataByEmail >>> 이메일 검색됨");
			System.out.println("_____________________________________________");
			return userdata;
		}
		else {//검색실패한 경우. 입력한 이메일과 일치하는 계정 없음.
			System.out.println("Impl_UserDAO.getUserDataByEmail >>> 검색실패, 입력한 이메일과 일치하는 계정 없음");
			System.out.println("_____________________________________________");
			return null;
		}
	}

	@Override
	public boolean checkEmailDuplicated(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.checkEmailDuplicated >>> 매서드 호출됨");
		
		int dupChecker = getSqlSession().selectOne("getCountByEmail", user_email);
		
		if(dupChecker == 0) {//일치하는 이메일 없음, 사용가능
			System.out.println("Impl_UserDAO.checkEmailDuplicated >>> 입력한 이메일 사용가능");
			System.out.println("_____________________________________________");
			return true;
		}
		else {//일치하는 이메일 존재, 입력된 이메일 사용 불가
			System.out.println("Impl_UserDAO.checkNicknameDuplicated >>> 이메일 중복 감지, 사용불가");
			System.out.println("_____________________________________________");
			return false;
		}
	}

	@Override
	public boolean checkNicknameDuplicated(String user_nickname) {
		// TODO Auto-generated method stub
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.checkNicknameDuplicated >>> 매서드 호출됨");
		
		int dupChecker = getSqlSession().selectOne("getCountByNickname", user_nickname);
		
		if(dupChecker == 0) {//일치하는 이메일 없음, 사용가능
			System.out.println("Impl_UserDAO.checkNicknameDuplicated >>> 입력한 닉네임 사용가능");
			System.out.println("_____________________________________________");
			return true;
		}
		else {//일치하는 이메일 존재, 입력된 이메일 사용 불가
			System.out.println("Impl_UserDAO.checkNicknameDuplicated >>> 닉네임 중복 감지, 사용불가");
			System.out.println("_____________________________________________");
			return false;
		}
	}
	
	@Override
	public boolean insertUserData(UserCommand newUser) {
		// TODO Auto-generated method stub
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.insertUserData >>> 매서드 호출됨");
		if( this.UserCommandChecker(newUser) == false ) {
			System.out.println("newUser data 무결성 체크 오류");
			return false;
		}
		if( this.checkEmailDuplicated(newUser.getUser_email()) == false ) {
			System.out.println("Impl_UserDAO.insertUserData >>> 이메일 중복됨");
			return false;
		}
		if( this.checkNicknameDuplicated(newUser.getUser_nickname()) == false ) {
			System.out.println("Impl_UserDAO.insertUserData >>> 닉네임 중복됨");
			return false;
		}
				
		int insertRES = getSqlSession().insert("insertUserDataInToTheCore", newUser);
		
		boolean insertChecker = false;
		
		if(insertRES != 0) {
			insertChecker = true;
			System.out.println("Impl_UserDAO.insertUserData >>> DB입력 성공");
		}
		else {
			insertChecker = false;
			System.out.println("Impl_UserDAO.insertUserData >>> DB입력 실패");
		}
		
		System.out.println("_____________________________________________");
		return insertChecker;
	}
	
	/*************************************************************************************
	 * 
	 * private 메서드, DAO 전용 메서드
	 * 
	 *************************************************************************************/
	private boolean UserCommandChecker(UserCommand data) {
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.UserCommandChecker >>> UserCommand 무결성 검사 시작");
		if(data == null) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> data is null");
			return false;
		}
		else if(data.getUser_email() == null) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> email is null");
			return false;
		}
		else if(data.getUser_password() == null) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> pw is null");
			return false;
		}
		else if(data.getUser_nickname() == null) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> nickname is null");
			return false;
		}
		else if(data.getUser_email().equals("")) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> email is empty");
			return false;
		}
		else if(data.getUser_password().equals("")) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> pw is empty");
			return false;
		}
		else if(data.getUser_nickname().equals("")) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> nickname is empty");
			return false;
		}
		else if(data.getImg_id() == -1) {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> imgID is empty");
			return false;
		}
		else {
			System.out.println("Impl_UserDAO.UserCommandChecker >>> UserCommand 무결성 검사 완료, 이상 무");
			System.out.println("_____________________________________________");
			return true;
		}
		
	}//UserCommandChecker

}
