package com.aka_guestbook.domain;

import java.util.Date;

public class GB_Guest_MsgCommand {
	private int 	gb_id;
	private String 	gb_writer_email;
	private	String	gb_content;
	private	Date	gb_regdate;
	private	int		gb_from_admin_id;
	public int getGb_id() {
		return gb_id;
	}
	public void setGb_id(int gb_id) {
		this.gb_id = gb_id;
	}
	public String getGb_writer_email() {
		return gb_writer_email;
	}
	public void setGb_writer_email(String gb_writer_email) {
		this.gb_writer_email = gb_writer_email;
	}
	public String getGb_content() {
		return gb_content;
	}
	public void setGb_content(String gb_content) {
		this.gb_content = gb_content;
	}
	public Date getGb_regdate() {
		return gb_regdate;
	}
	public void setGb_regdate(Date gb_regdate) {
		this.gb_regdate = gb_regdate;
	}
	public int getGb_from_admin_id() {
		return gb_from_admin_id;
	}
	public void setGb_from_admin_id(int gb_from_admin_id) {
		this.gb_from_admin_id = gb_from_admin_id;
	}
	
	
}
