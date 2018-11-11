package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductColorPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID")
	private int id;

	private String color;

}
