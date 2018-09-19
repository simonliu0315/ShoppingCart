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
@Table(name="USER_CONTRACT")
public class UserContract implements Serializable {

		private static final long serialVersionUID = 1L;

		@Id
		@GenericGenerator(name="g2", strategy="increment")
		@GeneratedValue(generator="g2")
		@Column(name="ID")
		private Long id;

		@Column
		private String username;
		
		@Column
		private String postName;
		
		@Column
		private String email;
		
		@Column
		private String zipCode;
		
		@Column
		private String city;
		
		@Column
		private String district;
		
		@Column
		private String address;
		
		@Column
		private String tel;
		
}
