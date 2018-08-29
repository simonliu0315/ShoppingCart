package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private BigDecimal originalPrice;

	private BigDecimal discount;
	
	private BigDecimal price;

	private String description;

	private String img;

	private int categoryId;

	private int activate;

	private int published;
	
	private int newest;
	
	private Date promotion_start;
	
	private Date promotion_end;
    
    private BigDecimal promotion_discount;
    
	private BigDecimal promotion_price;
    
    private BigDecimal promotion_on;

	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;
}
