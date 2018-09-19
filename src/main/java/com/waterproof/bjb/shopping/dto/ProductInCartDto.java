package com.waterproof.bjb.shopping.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProductInCartDto {

	private String userId;
	
	private List<ProductTempOrder> productsTempOrder;
	
	private int quantity;
	
	private String sessionId;
	
	private String orderNo;
	
	private BigDecimal subTotalAmt;
	
	private BigDecimal shipment;
	
	private BigDecimal totalAmt;
	
	
	public List<ProductTempOrder> getProductsTempOrder() {
		if (productsTempOrder == null) {
			productsTempOrder = new ArrayList<ProductTempOrder>();
			return productsTempOrder;
		} else {
		    return productsTempOrder;
		}
	}
	
	public BigDecimal getTotalAmt() {
		if (shipment == null) {
			shipment = BigDecimal.ZERO;
		}
		if (subTotalAmt == null) {
			subTotalAmt = BigDecimal.ZERO;
		}
		return shipment.add(subTotalAmt);
	}
	
	public BigDecimal getSubTotalAmt() {
		subTotalAmt = BigDecimal.ZERO;
		if (productsTempOrder != null) {
			for(ProductTempOrder productTempOrder : productsTempOrder) {
				subTotalAmt = subTotalAmt.add(productTempOrder.getPrice().multiply(new BigDecimal(productTempOrder.getQuantity())));
			
				//System.out.println("#######");
				//System.out.println(subTotalAmt + "+" + productTempOrder.getPrice() + "X" + productTempOrder.getQuantity());
				//System.out.println("#######");
			}
			return subTotalAmt;
		} else {
		    return BigDecimal.ZERO;
		}
	}
}
