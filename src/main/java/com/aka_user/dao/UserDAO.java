package com.aka_user.dao;

import com.aka_user.domain.UserCommand;

public interface UserDAO {
	public UserCommand getUserDataByEmail(String user_email);
	public boolean checkEmailDuplicated(String user_email );
	public boolean checkNicknameDuplicated(String user_nickname );
	public boolean insertUserData(UserCommand newUser);
}
