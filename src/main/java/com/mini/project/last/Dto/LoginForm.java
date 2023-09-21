package com.mini.project.last.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class LoginForm {
	
	private String userEmail;
	
	private String userPass;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	

}
