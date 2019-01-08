package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AuditLogPK id;

	private String ip;
	
	private String uri;
	
	private String insert_by; 
	
	@Column(name="HTTP_STATUS")
	private int httpStatus;
	
	@Column(name="HTTP_METHOD")
	private String httpMethod;
	
	@Column(name="JAVA_METHOD")
	private String javaMethod;
	
	@Column(name="RESPONSE")
	private String response;
	
}
