package com.mini.project.last.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class UnlockForm {
	
	private String email;
	
	private String userTempPass;
	
	private String newPass;
	
	private String confirmPass;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserTempPass() {
		return userTempPass;
	}

	public void setUserTempPass(String userTempPass) {
		this.userTempPass = userTempPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	
}
