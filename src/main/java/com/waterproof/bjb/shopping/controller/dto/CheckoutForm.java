package com.waterproof.bjb.shopping.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public @AllArgsConstructor @NoArgsConstructor @Data class CheckoutForm {

	private String postName;
	
	private String email;
	
	private String zipCode;
	
	private String county;
	
	private String district;
	
	private String address;
	
	private String tel;
	
	private int paymentMethod;
	
	private int invoiceType;
	
	private String vatId;
	
	private String businessName;
}
