package com.waterproof.bjb.shopping.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.entity.ProductColor;
import com.waterproof.bjb.shopping.entity.ProductColorPK;
import com.waterproof.bjb.shopping.entity.ProductTag;
import com.waterproof.bjb.shopping.repository.ProductColorRepository;
import com.waterproof.bjb.shopping.repository.ProductRepository;
import com.waterproof.bjb.shopping.repository.ProductTagRepository;
import com.waterproof.bjb.shopping.repository.impl.ProductRepositoryCustom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Resource
	private ProductRepositoryCustom productRepositoryCustom;

	@Resource
	private ProductRepository productRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@Autowired
	private ProductTagRepository productTagRepository;
	
	
	public Product getProduct(Long id) {
		Product product = productRepository.findOne(id);
		if (product != null) {
			product.setProductColors(productColorRepository.getProductColor(id.intValue()));	
		}
		return product;
	}
	
	public List<Product> getProductsOrderInsertTime() {
		return productRepository.getAllNewProduct();
	}
	
	public List<Product> getDiscountProductsOrderUpdatedTime() {
		return productRepository.getDiscountProduct();
	}
	
	public List<Product> getPromotionProduct() {
		return productRepository.getPromotionProduct(new Date());
	}
	
	public List<Product> getActivateProduct() {
		return productRepository.getAllActivateProduct();
	}
	
	public Page<Product> getFilterProduct(String q, int category, long productId,
    		long price_low, long price_high, int orderby, Pageable pageable, int[] tagId) {
		
		Page<Product> pproduct = productRepositoryCustom.filter(q, category, productId, price_low, price_high, orderby, pageable, tagId);
		List<Product> products = pproduct.getContent();
		log.info("size: {}" , products.size());
		for(Product product : products) {
			
			//product.setName(StringUtils.rightPad(product.getName(), 30, ""));
			log.info("product {}", product);
		}
		return pproduct;
	}

	public String getColorName(int productId, String color) {
		ProductColorPK id = new ProductColorPK();
		id.setProductId(productId);
		id.setColor(color);
		ProductColor productColor = productColorRepository.findOne(id);
		if (productColor != null) {
			return productColor.getName();
		} else {
			return null;
		}
	}
	
	public List<Product> getProducts() {
		return productRepository.getOrderByUpdatedDesc();
	}
}
