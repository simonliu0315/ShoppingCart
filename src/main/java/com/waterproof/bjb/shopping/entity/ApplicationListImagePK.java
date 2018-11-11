package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Embeddable
public class ApplicationListImagePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@GenericGenerator(name = "g4", strategy = "increment")
	@GeneratedValue(generator = "g4")
	@Column(name = "ID")
	private int id;

	private String name;

}
