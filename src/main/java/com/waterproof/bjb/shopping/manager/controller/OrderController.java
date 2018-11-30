package com.waterproof.bjb.shopping.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderDetail;
import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;
import com.waterproof.bjb.shopping.manager.service.OrderService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/order")
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.GET)
	public String getOrderList(ModelMap map) {
		log.info("getOrderList...");
		map.addAttribute("orderList", orderService.getAllOrder());
		return "manager/order/orderList";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable int id, ModelMap map) {
		log.info("orderlist");
		CustomerOrder customerOrder = orderService.getCustomerOrder(id);
		log.info("customerOrder {}", customerOrder);
		for (OrderDetail detail : customerOrder.getOrderDetails()) {
			log.info("detail {}", detail);
		}
		map.addAttribute("order", customerOrder);
		map.addAttribute("action", "update");
		return "manager/order/orderForm";
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		List<OrderDto> studentList = new ArrayList<OrderDto>();
		studentList.add(new OrderDto());
		studentList.add(new OrderDto());
		return new ModelAndView(new ExcelReportView(), "studentList", studentList);
	}

}
