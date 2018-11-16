package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_TAG")
public class ProductTag implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="g4", strategy="increment")
	@GeneratedValue(generator="g4")
	@Column(name="ID")
    private Long id;
	
	private String tag_name;
	
	private int status;
	
}