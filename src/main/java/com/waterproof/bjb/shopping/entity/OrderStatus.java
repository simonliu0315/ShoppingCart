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
@Table(name = "ORDER_STATUS")
public class OrderStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="g3", strategy="increment")
	@GeneratedValue(generator="g3")
	@Column(name="ID")
    private Long id;
	
	@Column
	private int status;
	
	@Column
	private String description;

	@Override
	public String toString() {
		return "OrderStatus [id=" + id + ", status=" + status + ", description=" + description + "]";
	}
	

	
	
}
