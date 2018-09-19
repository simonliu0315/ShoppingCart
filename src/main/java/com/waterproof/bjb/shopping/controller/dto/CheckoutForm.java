package com.waterproof.bjb.shopping.controller.dto;

import lombok.Data;

@Data
public class CheckoutForm {

	private String postName;
	
	private String email;
	
	private String zipCode;
	
	private String city;
	
	private String district;
	
	private String address;
	
	private String tel;
}
