package com.waterproof.bjb.shopping.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.service.CategoryService;
import com.waterproof.bjb.shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Resource
	private CategoryService categorySerivce;
	
	@Resource
	private ProductService productService;
	
	
	@RequestMapping(value = "/application_list/{id}", method = {RequestMethod.GET})
    public ModelAndView getSearchList(@PathVariable("id") int id) {
        log.info("application_list application_list");
        ModelAndView mav = new ModelAndView();
        mav.addObject("recommended_product", productService.getDiscountProductsOrderUpdatedTime().subList(0, 8));
        
        mav.addObject("APPLICATION", categorySerivce.findApplicationListById(id));
        mav.setViewName("category/application");
        return mav;
    }
}
