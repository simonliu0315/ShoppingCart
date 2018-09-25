package com.waterproof.bjb.shopping.controller.dto;

import lombok.Data;

@Data
public class LoginForm {

	private String username;
	
	private String password;
	
	private String captcha;
}
