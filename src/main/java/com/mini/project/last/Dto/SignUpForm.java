package com.mini.project.last.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class SignUpForm {

	private String userName;
	
	private String userEmail;
	
	private String userPhoneNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	
	

}
