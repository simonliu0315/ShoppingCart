package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class OrderDetailPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long orderId;

	private Long productId;

}
