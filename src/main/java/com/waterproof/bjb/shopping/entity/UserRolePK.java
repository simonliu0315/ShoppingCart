package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;


@Data
@Embeddable
public class UserRolePK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="USERNAME")
	private String username;

	@Column(name="ROLE")
	private String role;
	

	
}
