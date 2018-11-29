package com.waterproof.bjb.shopping.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.waterproof.bjb.shopping.utils.MailUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Resource
	private CategoryService categorySerice;
	
	@Resource
	private ProductService productService;
	
	@Autowired
	private MailUtil mailUtil;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "page", defaultValue = "0", required = false) String page,
    		HttpServletRequest request) {
        
		log.info("start index getPage.......");
        
        
        
        ModelAndView mav = new ModelAndView();
        if (request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART) == null) {
        	ProductInCartDto productInCart = new ProductInCartDto();
        	request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, productInCart);
        }
        mav.addObject("categorys", categorySerice.getAllCategory());
        //抓最新的商品資料
        mav.addObject("newest_product", productService.getProductsOrderInsertTime());
        //抓有特價的商品資料
        List<Product> promotionProducts = productService.getPromotionProduct();
        if (CollectionUtils.isNotEmpty(promotionProducts)) {
        	mav.addObject("promotion_product", promotionProducts);
        } else {
        	mav.addObject("promotion_product", new ArrayList<Product>());
        }
        
        for(Product product : promotionProducts) {
        	log.info("promotion_product {}", product);
        }
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
