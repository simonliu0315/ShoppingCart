package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.utils.NotifyUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/first_checkout")
public class FirstCheckOutController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private SimpleUserService userService;
	
	@Autowired
	private NotifyUtil util;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "postName", required=false) String postName, 
    		@RequestParam(value = "email", required=false) String email, @RequestParam(value = "zipCode", required=false) String zipCode,
    		@RequestParam(value = "city", required=false) String city, @RequestParam(value = "district", required=false) String district, 
    		@RequestParam(value = "address", required=false) String address, @RequestParam(value = "tel", required=false) String tel, 
    		@RequestParam(value = "paymentMethod", required=false, defaultValue = "1") int paymentMethod, 
    		@RequestParam(value = "invoiceType", required=false, defaultValue = "1") int invoiceType,
    		@RequestParam(value = "vatId", required=false, defaultValue = "") String vatId,
    		@RequestParam(value = "businessName", required=false, defaultValue = "") String businessName,
    		@RequestParam(value = "shippmentMethod", required=false, defaultValue = "1") int shippmentMethod,
    		HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        CheckoutForm checkoutForm = new CheckoutForm(postName, email, zipCode, city, district, address, tel, paymentMethod, invoiceType, vatId, businessName, shippmentMethod);
        log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("CheckoutForm", checkoutForm);
		mav.addObject("ShippingMethods", customerOrderService.getShipping());
		mav.addObject("bankCode", util.getBankCode());
		mav.addObject("accountNo", util.getAccountCode());
		mav.addObject("accountName", util.getAccountName());
        mav.setViewName("checkout/first_checkout");
        return mav;
	}	
}
