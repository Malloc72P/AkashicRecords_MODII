package com.aka_user.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.aka_user.domain.UserAuthorityCommand;
import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;

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
				
		int insertCoreRES 	= 	getSqlSession().insert("insertUserDataInToTheCore" , newUser);
		int insertLoginRES	= 	getSqlSession().insert("insertUserDataInToTheLogin", newUser);
		
		UserAuthorityCommand authData	=	new UserAuthorityCommand();
		authData.setUser_email(newUser.getUser_email());
		authData.setUser_password(newUser.getUser_password());
		authData.setAdmin_level(1);
		
		int insertAuthRES	=	getSqlSession().insert("insertAuthorityData", authData);	
		boolean insertChecker = false;
		
		if(insertCoreRES != 0 && insertLoginRES != 0 && insertAuthRES != 0) {
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

	@Override
	public boolean checkSuperUser(String user_email) {
		// TODO Auto-generated method stub
		int checkSuper	=	getSqlSession().selectOne("checkSuperUser", user_email);
		
		if(checkSuper != 0) {//Lv5이상인 유저인 경우
			return true;
		}
		else return false;
	}

	@Override
	public List<UserMetadataCommand> getUsersMetadata() {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("getUsersMetadata");
	}

	@Override
	public boolean mgr_updateUsrData(UserCommand userData) {
		// TODO Auto-generated method stub
		int updateChecker	=	getSqlSession().update("mgr_updateUsrData", userData);
		
		if(updateChecker != 0) {//Lv5이상인 유저인 경우
			return true;
		}
		else return false;
	}

	@Override
	public boolean mgr_updateUsrAuthority(UserMetadataCommand userMetaData) {
		// TODO Auto-generated method stub
		int updateChecker	=	getSqlSession().update("mgr_updateUsrAuthority", userMetaData);
		
		if(updateChecker != 0) {//Lv5이상인 유저인 경우
			return true;
		}
		else return false;
	}

	@Override
	public UserMetadataCommand getUserMetaDataByEmail(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("_____________________________________________");
		System.out.println("Impl_UserDAO.getUserMetaDataByEmail >>> 매서드 호출됨");
		UserMetadataCommand userdata = (UserMetadataCommand)getSqlSession().selectOne("getUserMetaDataByEmail",user_email);
		
		if(userdata != null) {//검색성공한 경우
			System.out.println("Impl_UserDAO.getUserMetaDataByEmail >>> 이메일 검색됨");
			System.out.println("_____________________________________________");
			return userdata;
		}
		else {//검색실패한 경우. 입력한 이메일과 일치하는 계정 없음.
			System.out.println("Impl_UserDAO.getUserMetaDataByEmail >>> 검색실패, 입력한 이메일과 일치하는 계정 없음");
			System.out.println("_____________________________________________");
			return null;
		}
	}

	@Override
	public boolean del_authData(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("______________________________________");
		System.out.println("Impl_UserDao.del_authData >>> 메서드 호출됨");
		System.out.println("user_email : "+user_email);
		int deleteChecker	=	getSqlSession().delete("del_authData", user_email);
		
		if(deleteChecker != 0) {//삭제 성공한 경우
			System.out.println("______________________________________");
			return true;
		}
		else {
			System.out.println("______________________________________");
			return false;
		}
	}

	@Override
	public boolean del_loginData(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("______________________________________");
		System.out.println("Impl_UserDao.del_loginData >>> 메서드 호출됨");
		int deleteChecker	=	getSqlSession().delete("del_loginData", user_email);
		
		if(deleteChecker != 0) {//삭제 성공한 경우
			System.out.println("______________________________________");
			return true;
		}
		else {
			System.out.println("______________________________________");
			return false;
		}
	}

	@Override
	public boolean del_userData(String user_email) {
		// TODO Auto-generated method stub
		System.out.println("______________________________________");
		System.out.println("Impl_UserDao.del_userData >>> 메서드 호출됨");
		int deleteChecker	=	getSqlSession().delete("del_userData", user_email);
		
		if(deleteChecker != 0) {//삭제 성공한 경우
			System.out.println("______________________________________");
			return true;
		}
		else {
			System.out.println("______________________________________");
			return false;
		}
	}

	@Override
	public boolean myPageUpdator(UserMetadataCommand userMetaData) {
		// TODO Auto-generated method stub
		int upChecker1 = 0;
		int upChecker2 = 0;
		int upChecker3 = 0;
		
		upChecker1 = getSqlSession().update("updateAkaUser", userMetaData);
		upChecker2 = getSqlSession().update("updateAkaUserAdmin", userMetaData);
		upChecker3 = getSqlSession().update("updateAkaUserLogin", userMetaData);
				
		if( (upChecker1 * upChecker2 * upChecker3) != 0 ) {
			return true;
		}
		else return false;
	}
	
}
