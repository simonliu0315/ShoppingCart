package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Embeddable
public class AuditLogPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "SESSION_ID")
	private String sessionId;
	
	@Column(name = "FUNCTION_ID")
	private String functionId;
	
	private Timestamp inserted;

}
