package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailPK id;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="DISCOUNT")
	private BigDecimal discount;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="QUANTITY")
	private Integer quantity;

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", price=" + price + ", discount=" + discount + ", productName=" + productName
				+ ", quantity=" + quantity + "]";
	}
	
	
}
