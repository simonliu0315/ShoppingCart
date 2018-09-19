package com.waterproof.bjb.shopping.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductTempOrder extends ProductDto {

	private static final long serialVersionUID = 1L;

	private int quantity = 1;
	
	private BigDecimal subTotalAmt;
	
	private boolean resetQuantity = false;
	
	public BigDecimal getSubTotalAmt() {
		return super.getPrice().multiply(new BigDecimal(this.quantity));
	}

	@Override
	public String toString() {
		return "ProductTempOrder [quantity=" + quantity + ", subTotalAmt=" + subTotalAmt + ", isResetQuantity="
				+ resetQuantity + ", getProductId()=" + getProductId() + ", getProductName()=" + getProductName()
				+ ", getOriginalPrice()=" + getOriginalPrice() + ", getPrice()=" + getPrice() + ", getDiscount()="
				+ getDiscount() + ", getDescription()=" + getDescription() + ", getImg()=" + getImg()
				+ ", getCategoryId()=" + getCategoryId() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
	
}
