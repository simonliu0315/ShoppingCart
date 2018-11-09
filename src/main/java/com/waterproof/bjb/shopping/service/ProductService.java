package com.waterproof.bjb.shopping.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.controller.IndexController;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.repository.ProductRepository;
import com.waterproof.bjb.shopping.repository.impl.ProductRepositoryCustom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Resource
	private ProductRepositoryCustom productRepositoryCustom;

	@Resource
	private ProductRepository productRepository;
	
	public Product getProduct(Long id) {
		return productRepository.findOne(id);
	}
	
	public List<Product> getProductsOrderInsertTime() {
		return productRepository.getAllNewProduct();
	}
	
	public List<Product> getDiscountProductsOrderUpdatedTime() {
		return productRepository.getDiscountProduct();
	}
	
	public List<Product> getPromotionProduct() {
		return productRepository.getPromotionProduct();
	}
	
	public List<Product> getActivateProduct() {
		return productRepository.getAllActivateProduct();
	}
	
	public Page<Product> getFilterProduct(String q, int category, long productId,
    		long price_low, long price_high, int orderby, Pageable pageable) {
		
		Page<Product> pproduct = productRepositoryCustom.filter(q, category, productId, price_low, price_high, orderby, pageable);
		List<Product> products = pproduct.getContent();
		log.info("size: {}" , products.size());
		for(Product product : products) {
			
			//product.setName(StringUtils.rightPad(product.getName(), 30, ""));
			log.info("product {}", product);
		}
		return pproduct;
	}
}
