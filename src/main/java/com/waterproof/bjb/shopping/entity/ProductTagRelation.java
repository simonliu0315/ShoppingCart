package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_TAG_RELATION")
public class ProductTagRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductTagRelationPK id;

	private int seq;
	
	private Timestamp inserted;

	private String insert_by;

	private Timestamp updated;

	private String update_by;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumns(		
	{
		@JoinColumn(updatable=false, insertable=false, name="PRODUCT_ID", referencedColumnName="ID"),
	}
	)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="TAG_ID", referencedColumnName="ID", updatable=false, insertable=false)
	private ProductTag productTag;

	@Override
	public String toString() {
		return "ProductTagRelation [id=" + id + ", inserted=" + inserted + ", insert_by=" + insert_by + ", updated="
				+ updated + ", update_by=" + update_by + ", product="  + ", productTag="  + "]";
	}
	
	
}
