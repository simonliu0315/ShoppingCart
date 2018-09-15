package com.waterproof.bjb.shopping.rest;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.dto.ProductDto;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.ProductService;
import com.waterproof.bjb.shopping.service.SimpleUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("user")
@RestController
public class UserRest {


	@Autowired
    private ProductService productService;
	
	@Autowired
	private SimpleUserService simpleUserService;
	
	@RequestMapping(value = "/numberOfProductInCart", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void numberOfProductInCart(
            HttpServletRequest request) {
		
	}
	
	@RequestMapping(value = "/addCart", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProductInCartDto addCart(@RequestBody ProductTempOrder productForm, 
            HttpServletRequest request) {
		log.info("call addCart. productId: {}",  productForm);
		Product product = productService.getProduct(Long.valueOf(productForm.getProductId()));
		
		ProductTempOrder productTempOrderDto = new ProductTempOrder();
		
		BeanUtils.copyProperties(product, productTempOrderDto);
		productTempOrderDto.setProductId(product.getId().intValue());
		productTempOrderDto.setProductName(product.getName());
        if (product.getPromotion_on().compareTo(BigDecimal.ONE) == 0) {
        	productTempOrderDto.setPrice(product.getPromotion_price());
        	productTempOrderDto.setDiscount(product.getPromotion_discount());
		}
		
		
		int quantity = productForm.getQuantity();
		log.info("After copy product info: {}", productTempOrderDto);
		ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
		log.info("session: {}", productInCartDto);
		boolean hasNotInCart = true;
		if (productInCartDto.getProductsTempOrder().size() == 0) {		
			productInCartDto.getProductsTempOrder().add(productTempOrderDto);
			productTempOrderDto.setQuantity(quantity);
		} else {
			for(int i = 0 ; i < productInCartDto.getProductsTempOrder().size() ; i++) {
				log.info("user products info {} in cart", productInCartDto.getProductsTempOrder().get(i));
				if(productInCartDto.getProductsTempOrder().get(i).getProductId() == productForm.getProductId()) {
					log.info("Same productId {} to break loop.", productInCartDto.getProductsTempOrder().get(i).getProductId());
					productInCartDto.getProductsTempOrder().get(i).setQuantity(productInCartDto.getProductsTempOrder().get(i).getQuantity() + quantity);
					if (product.getPromotion_on().compareTo(BigDecimal.ONE) == 0) {
						productInCartDto.getProductsTempOrder().get(i).setPrice(productTempOrderDto.getPrice());
						productInCartDto.getProductsTempOrder().get(i).setDiscount(productTempOrderDto.getDiscount());
					}
					
					hasNotInCart = false;
					break;
				}
			}
			if(hasNotInCart) {
				productInCartDto.getProductsTempOrder().add(productTempOrderDto);
			}
		}
		productInCartDto.setQuantity(productInCartDto.getProductsTempOrder().size());
		
		//設定使用者
		productInCartDto.setUserId(simpleUserService.getUser().getUsername());
		
		request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, productInCartDto);
		
		log.info("user cart is {}", productInCartDto);
		log.info("user cart product size {}", productInCartDto.getProductsTempOrder().size());
		
		return productInCartDto;
		
		
	}
	
	@RequestMapping(value = "/deleteCart", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProductInCartDto deleteCart(@RequestBody ProductDto productForm, 
            HttpServletRequest request) {
		log.info("call addCart. productId: {}",  productForm);
		Product product = productService.getProduct(Long.valueOf(productForm.getProductId()));
		ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
		log.info("session: {}", productInCartDto);
		
		if (product != null) {
			for(int i = 0 ; i < productInCartDto.getProductsTempOrder().size() ; i++) {
				log.info("user products info {} in cart", productInCartDto.getProductsTempOrder().get(i));
				if(productInCartDto.getProductsTempOrder().get(i).getProductId() == productForm.getProductId()) {
					productInCartDto.getProductsTempOrder().remove(i);
					break;
				}
			}
		}
		productInCartDto.setQuantity(productInCartDto.getProductsTempOrder().size());
		log.info("user cart is {}", productInCartDto);
		log.info("user cart product size {}", productInCartDto.getProductsTempOrder().size());
		return productInCartDto;
	}
	
	@RequestMapping(value = "/refashCart", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProductInCartDto refashCart(
            HttpServletRequest request) {
		ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCartDto == null) {
			productInCartDto = new ProductInCartDto();
		}
		productInCartDto.setQuantity(productInCartDto.getProductsTempOrder().size());
		log.info("session: {}", productInCartDto);
		log.info("user cart is {}", productInCartDto);
		log.info("user cart product size {}", productInCartDto.getProductsTempOrder().size());
		return productInCartDto;
	}
}
