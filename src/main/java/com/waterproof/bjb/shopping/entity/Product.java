package com.waterproof.bjb.shopping.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	private String name;
	
	private String engName;

	private BigDecimal originalPrice;

	private BigDecimal discount;
	
	private BigDecimal price;

	private String shortDescription;
	
	private String description;

	private String img;

	private int categoryId;

	private int activate;

	private int published;
	
	private int newest;
	
	private Date promotion_start;
	
	private Date promotion_end;
    
    private BigDecimal promotion_discount;
    
	private BigDecimal promotion_price;
    
    private BigDecimal promotion_on;

	private Timestamp inserted;

	private String insert_by;

	@Column(name = "UPDATED")
	private Timestamp updated;

	private String update_by;
	
	private int stock_quantity;
	
	private int usage;
	

	
	@Transient
	private String promotion_end_str;
	
	public String getPromotion_end_str() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		String text = df.format(promotion_end);
		return text;
	}
	
//	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, orphanRemoval = false)
//	@JoinColumn(name = "ID", referencedColumnName="PRODUCT_ID", insertable = false, updatable = false)
	@Transient
	private List<ProductColor> productColors;
	
	@OneToMany(targetEntity = ProductTagRelation.class, cascade = CascadeType.ALL, mappedBy = "product")
	private List<ProductTagRelation> productTagRelations;

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", engName=" + engName + ", originalPrice=" + originalPrice
				+ ", discount=" + discount + ", price=" + price + ", shortDescription=" + shortDescription
				+ ", description=" + description + ", img=" + img + ", categoryId=" + categoryId + ", activate="
				+ activate + ", published=" + published + ", newest=" + newest + ", promotion_start=" + promotion_start
				+ ", promotion_end=" + promotion_end + ", promotion_discount=" + promotion_discount
				+ ", promotion_price=" + promotion_price + ", promotion_on=" + promotion_on + ", inserted=" + inserted
				+ ", insert_by=" + insert_by + ", updated=" + updated + ", update_by=" + update_by
				+ ", promotion_end_str=" + promotion_end_str + ", productColors=" + productColors
				+ ", productTagRelations="  + "]";
	}
	
	
}
