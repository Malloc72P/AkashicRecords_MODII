package com.aka_user.domain;

public class UserMetadataCommand {
	//________________________________________
	private String 	user_email;
	private String 	user_nickname;
	private int 	img_id;
	private String 	validation;
	private int		admin_level;
	//————————————————————————————————————————————————————————————————————————————————————

	
	//————————————————————————————————————————————————————————————————————————————————————
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public String getValidation() {
		return validation;
	}
	public void setValidation(String validation) {
		this.validation = validation;
	}
	public int getAdmin_level() {
		return admin_level;
	}
	public void setAdmin_level(int admin_level) {
		this.admin_level = admin_level;
	}
	//————————————————————————————————————————————————————————————————————————————————————
}
