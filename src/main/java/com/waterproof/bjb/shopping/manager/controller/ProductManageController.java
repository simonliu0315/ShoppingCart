package com.waterproof.bjb.shopping.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/product")
public class ProductManageController {


	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getProduct() {
        ModelAndView mav = new ModelAndView();
      
        List<Product> products = productService.getProducts();
        log.info("product: {}", products);
        mav.addObject("products", products);
       
        mav.setViewName("manager/product/productList");
        return mav;
    }
	
	@RequestMapping(value = "/update/{id}", method = {RequestMethod.GET})
    public ModelAndView getId(@PathVariable("id") long id) {
        log.info("getId:{}", id);
        ModelAndView mav = new ModelAndView();
      
        Product product = productService.getProduct(id);
        log.info("product: {}", product);
        mav.addObject("product", product);
       
        mav.setViewName("manager/product/product-page");
        return mav;
    }
}
