package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.)
	// @GeneratedValue(strategy=GenerationType.AUTO)
	// @Column(name = "id", updatable = false, nullable = false)
	@GenericGenerator(name="g1", strategy="increment")
	@GeneratedValue(generator="g1")
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "ORDER_NO")
	private String orderNo;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "STATUS_ID")
	private int statusId;

	@Column(name = "INSERTED")
	private Timestamp inserted;

	@Column(name = "INSERT_BY")
	private String insert_by;

	@Column(name = "UPDATED")
	private Timestamp updated;

	@Column(name = "UPDATE_BY")
	private String update_by;

	@Column(name = "SHIPPING")
	private BigDecimal shipping;
	
	@Column(name = "PAYMENT_METHOD")
	private int method;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumn(name="STATUS_ID", referencedColumnName="STATUS", insertable = false, updatable = false)
	private OrderStatus orderStatus;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ORDER_NO", referencedColumnName="ORDER_NO", insertable = false, updatable = false)
	private List<OrderDetail> orderDetails;

	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumn(name="PAYMENT_METHOD", referencedColumnName="METHOD", insertable = false, updatable = false)
	private PaymentMethod paymentMethod;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumn(name="ORDER_NO", referencedColumnName="ORDER_NO", insertable = false, updatable = false)
	private OrderContract orderContract;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumn(name="ORDER_NO", referencedColumnName="ORDER_NO", insertable = false, updatable = false)
	private OrderInvoiceContract orderInvoiceContract;
	
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER) //移除, optional = false 就會正常
	@JoinColumn(name="ORDER_NO", referencedColumnName="ORDER_NO", insertable = false, updatable = false)
	private OrderATM orderATM;
	
	@Override
	public String toString() {
		return "CustomerOrder [id=" + id + ", orderNo=" + orderNo + ", amount=" + amount + ", username=" + username
				+ ", statusId=" + statusId + ", inserted=" + inserted + ", insert_by=" + insert_by + ", updated="
				+ updated + ", update_by=" + update_by + "]";
	}
	
	

}
