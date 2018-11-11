package com.waterproof.bjb.shopping.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waterproof.bjb.shopping.manager.service.OrderService;
import com.waterproof.bjb.shopping.service.UserService;

@Controller
@RequestMapping(value = "/manager/order") 
public class OrderController {

	@Autowired
    private UserService userService; 
	
	@Autowired
    private OrderService orderService; 
	
	@RequestMapping(method = RequestMethod.GET)
    public String getOrderList(ModelMap map) {
        map.addAttribute("orderList", orderService.getAllOrder());
        return "manager/order/orderList";
    }
}
