package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Entity
@Data
@IdClass(com.waterproof.bjb.shopping.entity.OrderDetailPK.class)
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long orderId;

	@Id
	private Long productId;

	
	private BigDecimal price;
	
	private int discount;
	
	private String productName;
	
	private int quantity;
	
}
