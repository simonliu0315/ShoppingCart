package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

//import org.springframework.data.annotation.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "APP_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "C_NAME")
	private String cName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@Column(name = "ZIP_CODE")
    private String zipCode;
	
	@Column(name = "COUNTY")
	private String county;
	
	@Column(name = "DISTRICT")
	private String district;
	
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "BIRTHDAY")
	private Date birthday;

	@Column(name = "VERIFY_CODE")
	private String verifyCode;

	@Column(name = "VERIFY_DATE")
	private Date verifyDate;

	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "MOBILE")
	private String mobile;

	@Transient
	private int isAdmin;
	
	@Transient
	private String birthdayStr;
	
	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getBirthdayStr() {
		if (birthday != null) {
			return ShoppingDateUtil.formatDate(birthday, "yyyy/MM/dd");
		} else {
			return "";
		}
	}
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = false)
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME", insertable = false, updatable = false)
	private List<UserRole> userRoles;

}
