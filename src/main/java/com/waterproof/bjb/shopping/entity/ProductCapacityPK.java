package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductCapacityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCT_ID")
	private int productId;

	private Number capacity;
	
	private String unit;

}
