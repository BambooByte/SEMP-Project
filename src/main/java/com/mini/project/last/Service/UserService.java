package com.mini.project.last.Service;

import com.mini.project.last.Dto.LoginForm;
import com.mini.project.last.Dto.SignUpForm;
import com.mini.project.last.Dto.UnlockForm;


public interface UserService {
	
	String userSignUp(SignUpForm signUpForm);
	
	boolean userLogIn(LoginForm loginForm);
	
	String unlockAcc(UnlockForm form);
	
	String getForgotPassword(String userEmail);

	
	
	

}
