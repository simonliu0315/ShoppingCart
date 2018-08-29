package com.waterproof.bjb.shopping.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDto {

	private Long id;
	
	private int type;
	
	private String name;
	
	private String img;
	
	private List<ProductDto> products;
}
