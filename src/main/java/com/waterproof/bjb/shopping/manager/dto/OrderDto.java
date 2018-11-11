package com.waterproof.bjb.shopping.manager.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderDto {

	private int id;
	
	private String orderNo;

	private BigDecimal amount;

	private String username;

	private Long statusId;

	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;

	private String statusDescription;
}
