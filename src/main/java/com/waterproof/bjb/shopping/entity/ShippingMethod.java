package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "SHIPPING_METHOD")
public class ShippingMethod implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="g3", strategy="increment")
	@GeneratedValue(generator="g3")
	@Column(name="ID")
    private Integer id;
	
	@Column
	private int method;
	
	@Column
	private String description;

	@Column
	private String engDescription;
	
	@Column
	private String content;
	
	@Column
	private int status;
	
	@Column
	private int shipping;
	
	

	
	
}
