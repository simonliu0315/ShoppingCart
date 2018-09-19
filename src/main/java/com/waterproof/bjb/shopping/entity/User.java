package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.Data;


@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERNAME")
	private String username;

	@Column(name="C_NAME")
	private String cName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	private String passwordConfirm;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="BIRTHDAY")
	private String birthday;
	
	
	@Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "USERNAME", referencedColumnName="USERNAME", insertable = false, updatable = false)
	private List<UserRole> userRoles;

}
