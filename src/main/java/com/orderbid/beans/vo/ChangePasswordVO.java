package com.orderbid.beans.vo;

public class ChangePasswordVO {
	
	
	private String currentpassword;
	private String newpassword;
	private String confirmpassword;
	public String getCurrentpassword() {
		return currentpassword;
	}
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@Override
	public String toString() {
		return "ChangePasswordVO [currentpassword=" + currentpassword
				+ ", newpassword=" + newpassword + ", confirmpassword="
				+ confirmpassword + "]";
	}
	
	
	
	

}
