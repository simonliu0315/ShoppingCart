package com.waterproof.bjb.shopping.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerOrderDto extends ProductInCartDto {

	private List<ProductInCartDto> productInCartDtos;
}
