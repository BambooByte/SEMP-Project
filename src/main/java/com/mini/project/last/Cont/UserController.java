package com.mini.project.last.Cont;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mini.project.last.Dto.LoginForm;
import com.mini.project.last.Dto.SignUpForm;
import com.mini.project.last.Dto.UnlockForm;
import com.mini.project.last.Service.UserService;

@Controller
@Validated
public class UserController {
	
	
	private UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	

	//SignUp....
	
		@GetMapping("/signup")
	    public String showSignupForm(Model model) {
	    model.addAttribute("signupDto", new SignUpForm());
	        return "signup";
	    }

	    @PostMapping("/sign")
	    public String processSignupForm(@ModelAttribute("signupDto") SignUpForm signupDto,
	                                    Model model) {
	     
	    
		    	String message = userService.userSignUp(signupDto);

		        model.addAttribute("message", message);

	     
	        
	    
	        return "signup";
	    }
	    
	    
	    
	    //Login.....
	    
	    @GetMapping("/login")
	    public String showLoginPage(
                Model model) {
	    	
	    	model.addAttribute("loginForm", new LoginForm());
	    	
	    	return "login";
	    }
	    
	    

	    @PostMapping("/login")
	    public String processLoginForm( @ModelAttribute("loginForm") LoginForm loginForm,
	                                    Model model  ) throws Exception {
	    	
	    	boolean message = userService.userLogIn(loginForm);
	    	
	    if (message) {
	    	
	    	 return "redirect:/dashboard";
	    }
		
	   
	    model.addAttribute("msg", "Invalid Email or Password , Check Email If Not Unlocked Account");
	    
		return "login";
	    	
	    }
	        
	      
	        
	    
	        
	       
	    
	    
	    //Forgot Password....
	    
	    @GetMapping("/forgot")
	    public String showForgotPasswordPage() {
	    	
	    	return "forgotPwd";
	    }
	    
	    
	    @PostMapping("/forgot")
	    public String DoForgotPasswordPage(@RequestParam("email") String email, Model model) {
	    	
	    	String mesg = userService.getForgotPassword(email);
	    	
	    	model.addAttribute("mesg", mesg);
	    	
	    	return "forgotPwd";
	    }

	    
	    //Unlock.....
	    
	    
	    @GetMapping("/unlock")
	    public String showUnlockAcc(
	    		Model model
	    		,@RequestParam(name = "email" ) String email) {
	    	
	    	model.addAttribute("unlockAcc", new UnlockForm());
	    	model.addAttribute("email",email);
	    	
	    	return "unlock";
	    }
	    
	    
	    @PostMapping("/unlock")
	    public String unlockAcc(@ModelAttribute("unlockAcc") UnlockForm unlockAcc,
	                                    Model model) {
	    
	    	model.addAttribute("unlockAcc", new UnlockForm());
	    	if(unlockAcc.getNewPass().equals(unlockAcc.getConfirmPass())) {
	        
	    	String message = userService.unlockAcc(unlockAcc);
	    	
	        model.addAttribute("message", message);
	        
	    	}else {
	    		String message="password and Confirm Password Not Same..";
	    		model.addAttribute("message", message);
	    	}
	        return "unlock";
	    }
	    
	}

	
	


