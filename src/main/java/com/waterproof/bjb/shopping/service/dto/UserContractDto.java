package com.waterproof.bjb.shopping.service.dto;

import lombok.Data;

@Data
public class UserContractDto {

	private String username;
	
	private String postName;
	
	private String email;
	
	private String zipCode;
	
	private String county;
	
	private String district;
	
	private String address;
	
	private String tel;
	
    private int invoiceType;
	
	private String vatId;
	
	private String businessName;
}
