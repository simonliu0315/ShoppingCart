package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@EmbeddedId
	private UserRolePK id;
	
	@Column(name="STATUS")
	private int status;
	
}
