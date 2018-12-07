package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_ATM")
public class OrderATM implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ORDER_NO")
	private String orderNo;

	@Column(name="LAST_FIVE_ACCOUNT_NO")
	private String lastFiveAccountNo;

	@Column(name="ACCOUNT_NAME")
	private String accountName;

	

}
