package com.waterproof.bjb.shopping.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.CategoryService;
import com.waterproof.bjb.shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Resource
	private CategoryService categorySerice;
	
	@Resource
	private ProductService productService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "page", defaultValue = "0", required = false) String page,
    		HttpServletRequest request) {
        log.info("index getPage");
        ModelAndView mav = new ModelAndView();
        if (request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART) == null) {
        	ProductInCartDto productInCart = new ProductInCartDto();
        	request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, productInCart);
        }
        mav.addObject("categorys", categorySerice.getAllCategory());
        //抓最新的商品資料
        mav.addObject("newest_product", productService.getProductsOrderInsertTime());
        //抓有特價的商品資料
        mav.addObject("promotion_product", productService.getPromotionProduct());
        if (productService.getPromotionProduct() != null && productService.getPromotionProduct().size() > 0) {
            mav.addObject("promotion_product_one", productService.getPromotionProduct().get(0));
        } else {
        	mav.addObject("promotion_product_one", new ArrayList<Product>());
        }
        //抓有折扣的商品資料
        mav.addObject("discount_product", productService.getDiscountProductsOrderUpdatedTime());
        //抓最常購買的資料
        mav.addObject("suggest_product", productService.getDiscountProductsOrderUpdatedTime());
        //抓使用者建議的商品
        mav.addObject("recommended_product", productService.getDiscountProductsOrderUpdatedTime()); 
        mav.setViewName("index");
        return mav;
    }
	
}
