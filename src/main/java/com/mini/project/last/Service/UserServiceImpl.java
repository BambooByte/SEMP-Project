package com.mini.project.last.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.project.last.Dto.LoginForm;
import com.mini.project.last.Dto.SignUpForm;
import com.mini.project.last.Dto.UnlockForm;
import com.mini.project.last.Model.UserEntity;
import com.mini.project.last.Repo.UserRepo;
import com.mini.project.last.Utils.EmailUtils;
import com.mini.project.last.Utils.PasswordUtils;
import com.mini.project.last.Utils.UserNotFoundException;
import com.mini.project.last.Utils.stringMesg;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordUtils passwordUtils;
	
	@Autowired
	private EmailUtils emailUtils;
	

	@Override
	public String userSignUp(SignUpForm signUpForm) {
	
		
		
		 try {
				
				UserEntity mail = userRepo.findByUserMail(signUpForm.getUserEmail());

			
		if(null != mail) {
		
			return stringMesg.USER_RES_STR;
		
		}
		
		
		
		}catch (UserNotFoundException e) {
			throw	new  UserNotFoundException("Invalid Email Or Password");		}
		

			UserEntity userEntity = UserEntity.builder().userName(signUpForm.getUserName())
								.userMail(signUpForm.getUserEmail())
								.userContactNumber(signUpForm.getUserPhoneNo())
								.userPassword(passwordUtils.passwordGenerator())
								.accStatus("LOCKED")
								.build();
			
			userRepo.save(userEntity);
			
			String to= signUpForm.getUserEmail();
			String sub=stringMesg.USER_MAIL_SUB;
			
			StringBuffer buffer=new StringBuffer("");
			
			buffer.append("<h1>temp pass: " + userEntity.getUserPassword() + "</h1>");
			buffer.append("<a href=\"http://localhost:7040/unlock?email=" + to + "\">Click Here To Unlock Account</a>");
			
			emailUtils.emailGenerator(to, sub, buffer.toString());
			
			return "Check Email For Unlock Account";
			
		
		
	}

	@Override
	public boolean userLogIn(LoginForm loginForm) {
	
		
		 UserEntity ent = userRepo.findByUserMail(loginForm.getUserEmail());
		 try {
		// throw	new  UserNotFoundException("Invalid Email Or Password");
	
		if(ent == null) {
			return false;
		}
		
		if(!ent.getUserPassword().equals(loginForm.getUserPass())) {
			return false;
		}
		
		
		if(ent.getAccStatus().equals("LOCKED")) {
			return false;
		}}catch (UserNotFoundException e) {
			
			throw	new  UserNotFoundException("Invalid Email Or Password");
		}
		
		System.out.println(ent.getUserId());
		httpSession.setAttribute("userid", ent.getUserId());
		
		
	 
		
		return true;}
	

	
	@Override
	public String unlockAcc(UnlockForm form) {
		
		
		UserEntity userMail = userRepo.findByUserMail(form.getEmail());
		
		if(!userMail.getUserPassword().equals(form.getUserTempPass())) {
			
			return "Invalid Temp Password";
		}else {
			
			userMail.setUserPassword(form.getNewPass());
			userMail.setAccStatus("UNLOCKED");
			userRepo.save(userMail);

			return "Password Updated Successfully..";
		}

	}
	

	

	@Override
	public String getForgotPassword(String userEmail) {
		
		UserEntity entity = userRepo.findByUserMail(userEmail);
		
		if (entity == null) {
            return "Email not registered, please register first.";
        }
		 if (entity.getAccStatus().equals("LOCKED")) {
	            return "Account Locked, Unlock First..";
	    }
		 
		 String to= userEmail;
		 String sub= "Check Your Password";
		 String body=entity.getUserPassword();
		 
		 emailUtils.emailGenerator(to, sub, body);

		return "Check Your Email..";
	}

	

}
