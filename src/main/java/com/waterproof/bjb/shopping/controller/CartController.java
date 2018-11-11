package com.waterproof.bjb.shopping.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.ProductService;
import com.waterproof.bjb.shopping.service.SimpleUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@Autowired
    private ProductService productService;
	
	@Autowired
	private SimpleUserService simpleUserService;
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView getPage(HttpServletRequest request) {
		log.info("index getPage");
		ModelAndView mav = new ModelAndView();
		ProductInCartDto productInCart = (ProductInCartDto) request.getSession()
				.getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCart == null) {
			productInCart = new ProductInCartDto();
		}
		mav.addObject(SessionParameter.PRODUCTS_IN_CART, productInCart);
		mav.setViewName("cart/cart");
		return mav;
	}

}
