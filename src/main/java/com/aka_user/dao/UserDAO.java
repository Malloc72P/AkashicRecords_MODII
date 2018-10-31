package com.aka_user.dao;

import java.util.List;

import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;

public interface UserDAO {
	//###SELECT DATA###
	public UserCommand					getUserDataByEmail(String user_email);
	public UserMetadataCommand			getUserMetaDataByEmail(String user_email);
	public List<UserMetadataCommand>	getUsersMetadata();
	public boolean						checkSuperUser(String user_email);
	public boolean						checkEmailDuplicated(String user_email );
	public boolean						checkNicknameDuplicated(String user_nickname );
	//###INSERT DATA###
	public boolean						insertUserData(UserCommand newUser);
	//###UPDATE DATA###
	public boolean						mgr_updateUsrData(UserCommand userData);
	public boolean						mgr_updateUsrAuthority(UserMetadataCommand userMetaData);
	public boolean						myPageUpdator(UserMetadataCommand userMetaData);
	//###DELETE DATA###
	public boolean						del_authData(String user_email);
	public boolean						del_loginData(String user_email);
	public boolean						del_userData(String user_email);
	
	
	
	
	
	
	
	
}
