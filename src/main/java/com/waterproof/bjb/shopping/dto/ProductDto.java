package com.waterproof.bjb.shopping.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {

	private int productId; 
	
	private String productName;

	private BigDecimal originalPrice;
	
	private BigDecimal price;

	private BigDecimal discount;

	private String description;

	private String img;

	private int categoryId;
	
}
