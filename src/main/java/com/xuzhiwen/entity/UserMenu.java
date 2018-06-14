package com.xuzhiwen.entity;

public class UserMenu {
	private String user_id;
	private String menu_name;
	
	@Override
	public String toString() {
		return "UserMenu [user_id=" + user_id + ", menu_name=" + menu_name + "]";
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
}
