package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_COLOR")
public class ProductColor implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductColorPK id;

	private String name;
	
	private String eName;
	
	private String linnerColor;	

	private String status;

	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;
	
	private int seq;
		
}
