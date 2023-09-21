package com.mini.project.last.Except;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mini.project.last.Utils.UserNotFoundException;


@ControllerAdvice
public class GlobalExcept {
	
	    
		@ExceptionHandler(UserNotFoundException.class)
		public String handleException(UserNotFoundException ex) {
			
			String message = ex.getMessage();
		   
		    return message;
		}
	}



