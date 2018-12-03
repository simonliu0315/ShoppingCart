package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderDetailPK id;
	
	@Column(name="PRICE")
	private BigDecimal price;
	
	@Column(name="DISCOUNT")
	private BigDecimal discount;
	
	@Column(name="PRODUCT_NAME")
	private String productName;
	
	@Column(name="QUANTITY")
	private Integer quantity;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumns(		
	{
		@JoinColumn(updatable=false, insertable=false, name="PRODUCT_ID", referencedColumnName="PRODUCT_ID"),
		@JoinColumn(updatable=false, insertable=false, name="COLOR", referencedColumnName="COLOR")
	}
	)
	@NotFound(action = NotFoundAction.IGNORE)
	private ProductColor productColor;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns(		
	{
		@JoinColumn(updatable=false, insertable=false, name="PRODUCT_ID", referencedColumnName="ID"),
	}
	)
	private Product product;
	
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", price=" + price + ", discount=" + discount + ", productName=" + productName
				+ ", quantity=" + quantity + "]";
	}
	
	
}
