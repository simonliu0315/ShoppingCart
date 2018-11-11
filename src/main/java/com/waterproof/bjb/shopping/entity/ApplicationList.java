package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "APPLICATION_LIST")
public class ApplicationList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "g4", strategy = "increment")
	@GeneratedValue(generator = "g4")
	@Column(name = "ID")
	private int id;

	private String name;

	private String content;

	private String status;

	private String img;

	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;
	
	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ID", referencedColumnName="ID", insertable = false, updatable = false)
	private List<ApplicationListImage> applicationListImages;
}
