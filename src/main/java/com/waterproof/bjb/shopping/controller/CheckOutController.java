package com.waterproof.bjb.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.service.dto.UserContractDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/checkout")
public class CheckOutController {

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private SimpleUserService userService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "postName", required=false) String postName, 
    		@RequestParam(value = "email", required=false) String email, @RequestParam(value = "zipCode", required=false) String zipCode,
    		@RequestParam(value = "city", required=false) String city, @RequestParam(value = "district", required=false) String district, 
    		@RequestParam(value = "address", required=false) String address, @RequestParam(value = "tel", required=false) String tel, 
    		@RequestParam(value = "paymentMethod", required=false, defaultValue = "1") int paymentMethod, HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        CheckoutForm checkoutForm = new CheckoutForm(postName, email, zipCode, city, district, address, tel, paymentMethod);
        log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("CheckoutForm", checkoutForm);
        mav.setViewName("checkout/checkout");
        return mav;
    }
	
	@RequestMapping(value = "", method = {RequestMethod.POST})
    public ModelAndView getPagePost(@ModelAttribute CheckoutForm formBean, HttpServletRequest request) {
        log.info("getPagePost page form {}", formBean);
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        productInCartDto.setPaymentMethod(formBean.getPaymentMethod());
        String username = userService.getUser().getUsername();
        if (StringUtils.isBlank(username)) {
        	mav.setViewName("/");
        	return mav;
        }
        productInCartDto.setUserId(username);
        log.info("session: {}", productInCartDto);
        UserContractDto userContractDto = new UserContractDto();
        BeanUtils.copyProperties(formBean, userContractDto);
        log.info("login user {}", userService.getUser());
        String orderNumber = customerOrderService.saveToOrder(userContractDto, productInCartDto, username);
        request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, null);
        ProductInCartDto dto = customerOrderService.queryOrderByNo(orderNumber);
        log.info("dto :{}", dto);
        List<ProductInCartDto> list = new ArrayList<ProductInCartDto>();
        list.add(dto);
        mav.addObject("orderNumber", orderNumber);
		mav.addObject("orders", list);
        mav.setViewName("order/list");
        return mav;
    }
}
