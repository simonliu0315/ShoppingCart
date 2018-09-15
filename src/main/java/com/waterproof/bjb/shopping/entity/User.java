package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.Data;

@Entity
@Table
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String username;

	@Column(name="C_NAME")
	private String cName;
	@Column
	private String email;
	@Column
	private String password;
	
	private String passwordConfirm;
	
	@Column
	private String address;
	
	@Column
	private String birthday;
	
	
	@Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
