package com.waterproof.bjb.shopping.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {

	private String name;
	
	private List<ProductDto> products;
}
