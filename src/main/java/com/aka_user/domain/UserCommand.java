package com.aka_user.domain;

public class UserCommand {
	//________________________________________계정정보
	private String 	user_email;
	private String 	user_password;
	private String 	user_nickname;
	private String	validation;
	private int img_id;
	
	//————————————————————————————————————————————————————————————————————————————————————
	


	//————————————————————————————————————————————————————————————————————————————————————
	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	//————————————————————————————————————————————————————————————————————————————————————
}
