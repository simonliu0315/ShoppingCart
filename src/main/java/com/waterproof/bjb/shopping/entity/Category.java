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
@Table(name = "CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="g4", strategy="increment")
	@GeneratedValue(generator="g4")
	@Column(name="ID")
    private Long id;
	
	private String name;
	
	private int type;
	
	private String img;
	
	private String urlView;
	
	private Timestamp inserted;
	
	private String insert_by;
	
	private Timestamp updated;
	
	private String update_by;
	
	private String status;
}
