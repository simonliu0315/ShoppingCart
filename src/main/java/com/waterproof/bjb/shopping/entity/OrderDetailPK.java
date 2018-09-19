package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.Data;

@Data
@Embeddable
public class OrderDetailPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="ORDER_NO")
	private String orderNo;
	
	@Column(name="PRODUCT_ID")
	private Integer productId;

}
