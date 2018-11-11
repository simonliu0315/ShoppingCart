package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "APPLICATION_LIST_IMAGE")
public class ApplicationListImage implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ApplicationListImagePK id;
	
	private String img;

	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;
}
