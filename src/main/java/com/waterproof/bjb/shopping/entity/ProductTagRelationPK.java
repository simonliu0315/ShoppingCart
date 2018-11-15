package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductTagRelationPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "TAG_ID")
	private int tagId;

}
