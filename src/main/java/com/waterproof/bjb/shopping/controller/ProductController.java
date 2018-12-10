package com.waterproof.bjb.shopping.controller;

import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.entity.ApplicationList;
import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.CategoryService;
import com.waterproof.bjb.shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/products")
public class ProductController {

	@Resource
	private ProductService productService;
	
	@Resource
	private CategoryService categorySerivce;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(HttpServletRequest request) {
        log.info("index getPage");
        ModelAndView mav = new ModelAndView();
        Pageable pageable = new PageRequest(0, 12);
        log.info("page {}, pageSize {}", 0, 12);
        Page<Product> products = productService.getFilterProduct("", 0, 0, 0, 0, 1, pageable, null);
        log.info("products size {}", products.getContent().size());
        mav.addObject("activate_product", products.getContent());
        log.info("getTotalPages {}", products.getTotalPages());
        log.info("pageSize {}", products.getSize());
        mav.addObject("totalPages", products.getTotalPages());
        mav.addObject("pageSize", products.getSize());
        mav.addObject("pageNumber", pageable.getPageNumber());
        mav.addObject("pageable", pageable);
        log.info("pageNumber {}, Offset {}, pageSize {}", pageable.getPageNumber(), pageable.getOffset(), pageable.getPageSize());
        String defaultUrl = "&";
        defaultUrl += "page=1";
        defaultUrl += "&pageSize=12";
        mav.addObject("default_url", defaultUrl);
        String defaultUrlPage = "?";
        defaultUrlPage += "pageSize=12&page=";
        mav.addObject("default_url_page", defaultUrlPage);
        mav.addObject("filter_price_low", 0);
        mav.addObject("filter_price_high", 0);
        //抓最常購買的資料
        mav.addObject("suggest_product", productService.getDiscountProductsOrderUpdatedTime().subList(0, 5));
        String disp = (request.getParameter("disp") == null ? "" : request.getParameter("disp")).toString();
        if ("list".equals(disp)) {
        	mav.setViewName("product/products_list");
        } else {
        	mav.setViewName("product/products");
        }
        
        return mav;
    }
	@RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ModelAndView getId(@PathVariable("id") long id) {
        log.info("getId:{}", id);
        ModelAndView mav = new ModelAndView();
      
        Product product = productService.getProduct(id);
        log.info("product: {}", product);
        mav.addObject("product", product);
       
        mav.addObject("recommended_product", productService.getDiscountProductsOrderUpdatedTime().subList(0, 4));
        
        mav.setViewName("product/product-page");
        return mav;
    }
	
	@RequestMapping(value = "/list/category/{id}", method = {RequestMethod.GET})
    public ModelAndView getCategoryList(@PathVariable("id") long id) {
        log.info("index getPage");
        ModelAndView mav = new ModelAndView();
      
        mav.setViewName("product/products");
        return mav;
    }
	
	@RequestMapping(value = "/search/{id}", method = {RequestMethod.GET})
    public ModelAndView getSearchList(@PathVariable("id") long id) {
        log.info("index getPage");
        ModelAndView mav = new ModelAndView();
      
        mav.setViewName("product/products");
        return mav;
    }
	
	@RequestMapping(value = "/search", method = {RequestMethod.GET})
    public ModelAndView getSearchNameList(@RequestParam(value = "q", required=false, defaultValue = "") String q, 
    		@RequestParam(value = "category", required=false, defaultValue = "0") int category, 
    		@RequestParam(value = "productId", required=false, defaultValue = "0") long productId,
    		@RequestParam(value = "price_low", required=false, defaultValue = "0") long price_low,
    		@RequestParam(value = "price_high", required=false, defaultValue = "0") long price_high,
    		@RequestParam(value = "orderby", required=false, defaultValue = "1") int orderby,
    		@RequestParam(value = "page", required=false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required=false, defaultValue = "12") int pageSize,
    		@RequestParam(value = "tagId", required=false) int[] tagId) {
        log.info("search getPage");
        
        ModelAndView mav = new ModelAndView();
        if (page != 0) {
        	page = page - 1;
        }
        log.info("page {}, pageSize {}", page, pageSize);
        Pageable pageable = new PageRequest(page, pageSize);
        
        Category categoryEntity = categorySerivce.findCategoryById(new Long(category));
        if (categoryEntity != null && !StringUtils.isEmpty(categoryEntity.getUrlView())) {
        	
        	pageable = new PageRequest(page, pageSize);
        	Page<ApplicationList> pApplicationList = categorySerivce.getFilterApplication(q, orderby, pageable);
        	mav.addObject("APPLICATION_LIST", pApplicationList.getContent());
        	String defaultUrlPage = "&";
            defaultUrlPage += "pageSize="+ pageSize + "&page=";
            mav.addObject("default_url_page", defaultUrlPage);
            mav.addObject("totalPages", pApplicationList.getTotalPages());
            mav.addObject("pageSize", pApplicationList.getSize());
            mav.addObject("pageNumber", pageable.getPageNumber());
            mav.addObject("pageable", pageable);
        	mav.setViewName(categoryEntity.getUrlView());
        	return mav;
        } 

        Page<Product> products = productService.getFilterProduct(q, category, productId, price_low, price_high, orderby, pageable, tagId);
        log.info("products size {}", products.getContent().size());
        mav.addObject("activate_product", products.getContent());
        log.info("getTotalPages {}", products.getTotalPages());
        log.info("pageSize {}", products.getSize());
        mav.addObject("totalPages", products.getTotalPages());
        mav.addObject("pageSize", products.getSize());
        mav.addObject("pageNumber", pageable.getPageNumber());
        mav.addObject("pageable", pageable);
        String defaultUrl = "&";
        if (!StringUtils.isEmpty(q)) {
        	defaultUrl += "q=" + q;
        }
        if (category != 0) {
        	defaultUrl += "&category=" + category;
        }
        if (productId != 0) {
        	defaultUrl += "&productId=" + productId;
        }
        if (price_low != 0) {
        	defaultUrl += "&price_low=" + price_low;
        }
        if (price_high != 0) {	
        	defaultUrl += "&price_high=" + price_high;
        }
        if (orderby != 1) {
        	defaultUrl += "&orderby=" + orderby;
        }
        defaultUrl += "&page=" + page;
        defaultUrl += "&pageSize=" + pageSize;
        mav.addObject("default_url", defaultUrl);

        log.info("pageNumber {}, Offset {}, pageSize {}", pageable.getPageNumber(), pageable.getOffset(), pageable.getPageSize());
        StringBuilder sb = new StringBuilder("");
        
        //分頁連結用
        String defaultUrlPage = "";
        if (tagId != null && tagId.length > 0) {
            for(int t: tagId) {
            	mav.addObject("active" + t, true);
            	sb.append("" + t +",");
            }
            defaultUrlPage += "&tagId=" + sb.substring(0, sb.length()  - 1);
            defaultUrlPage += "&pageSize="+ pageSize + "&page=";
        } else {
        	if (category != 0) {
            	defaultUrlPage += "&category=" + category;
            }
        	if (productId != 0) {
        		defaultUrlPage += "&productId=" + productId;
            }
            if (price_low != 0) {
            	defaultUrlPage += "&price_low=" + price_low;
            }
            if (price_high != 0) {	
            	defaultUrlPage += "&price_high=" + price_high;
            }
            if (orderby != 1) {
            	defaultUrlPage += "&orderby=" + orderby;
            }
        	defaultUrlPage += "&pageSize="+ pageSize + "&page=";
        }
        
        
        
        log.info("defaultUrlPage {}", defaultUrlPage);
        mav.addObject("default_url_page", defaultUrlPage);
        mav.addObject("filter_price_low", price_low);
        mav.addObject("filter_price_high", price_high);
        mav.addObject("filter_tagId", tagId);
        

        
        //抓最常購買的資料
        mav.addObject("suggest_product", productService.getDiscountProductsOrderUpdatedTime().subList(0, 5));
        mav.setViewName("product/products");
        return mav;
    }
	
	
}
