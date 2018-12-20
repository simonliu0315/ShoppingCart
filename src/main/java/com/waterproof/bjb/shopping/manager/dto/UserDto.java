package com.waterproof.bjb.shopping.manager.dto;

import lombok.Data;

@Data
public class UserDto {

	private String username;

	private String cName;
	
	private String email;

	private String password;

    private String zipCode;
	
	private String county;
	
	private String district;
	
	private String address;

	private String birthdayStr;
	
	private String verifyCode;

	private String verifyDate;

	private int status;
	
	private String mobile;

	private int isAdmin;
}
