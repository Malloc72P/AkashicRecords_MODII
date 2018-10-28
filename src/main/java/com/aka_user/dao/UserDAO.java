package com.aka_user.dao;

import java.util.List;

import com.aka_user.domain.UserCommand;
import com.aka_user.domain.UserMetadataCommand;

public interface UserDAO {
	public UserCommand					getUserDataByEmail(String user_email);
	public boolean						checkEmailDuplicated(String user_email );
	public boolean						checkNicknameDuplicated(String user_nickname );
	public boolean						insertUserData(UserCommand newUser);
	public boolean						checkSuperUser(String user_email);
	public List<UserMetadataCommand>	getUsersMetadata();
	public boolean						mgr_updateUsrData(UserCommand userData);	
}
