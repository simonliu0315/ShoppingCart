package com.waterproof.bjb.shopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
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
	private SimpleUserService uerService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        ProductInCartDto productInCartDto = (ProductInCartDto)request.getSession().getAttribute(SessionParameter.PRODUCTS_IN_CART);
        if (productInCartDto == null) {
        	productInCartDto = new ProductInCartDto();
        }
        log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("CheckoutForm", new CheckoutForm());
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
        String username = uerService.getUser().getUsername();
        productInCartDto.setUserId(username);
        log.info("session: {}", productInCartDto);
        UserContractDto userContractDto = new UserContractDto();
        BeanUtils.copyProperties(formBean, userContractDto);
        log.info("login user {}", uerService.getUser());
        String orderNumber = customerOrderService.saveToOrder(userContractDto, productInCartDto, username);
        
        List<ProductInCartDto> productInCartDtos = customerOrderService.queryOrder(username);
        mav.addObject("orderNumber", orderNumber);
		mav.addObject("orders", productInCartDtos);
        mav.setViewName("order/list");
        return mav;
    }
}
