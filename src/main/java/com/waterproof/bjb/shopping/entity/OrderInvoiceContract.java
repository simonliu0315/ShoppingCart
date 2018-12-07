package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_INVOICE_CONTRACT")
public class OrderInvoiceContract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column
	private String postName;

	@Column
	private String email;

	@Column
	private String zipCode;

	@Column
	private String county;

	@Column
	private String district;

	@Column
	private String address;

	@Column
	private String tel;

	@Column(name="INVOICE_NO")
	private String invoiceNo;
	
	@Column(name="INVOICE_TYPE")
	private int invoiceType;
	
	@Column(name="VAT_ID")
	private String vatId;
	
	@Column(name="BUSINESS_NAME")
	private String businessName;
}
