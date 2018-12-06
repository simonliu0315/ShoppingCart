package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_TAG")
public class ProductTag implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="g4", strategy="increment")
	@GeneratedValue(generator="g4")
	@Column(name="ID")
    private int id;
	
	private String tag_name;
	
	private int status;
	
    @OneToMany(targetEntity = ProductTagRelation.class, cascade = CascadeType.ALL, mappedBy = "productTag")
	private List<ProductTagRelation> productTagRelations;

	@Override
	public String toString() {
		return "ProductTag [id=" + id + ", tag_name=" + tag_name + ", status=" + status + ", productTagRelations="
				+ productTagRelations + "]";
	}
    
    
}
