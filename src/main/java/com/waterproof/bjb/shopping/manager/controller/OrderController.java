package com.waterproof.bjb.shopping.manager.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getSearchNameList(@RequestParam(value = "orderNo", required=false, defaultValue = "") String orderNo, 
    		@RequestParam(value = "statusId", required=false, defaultValue = "0") int statusId, 
    		@RequestParam(value = "startDate", required=false, defaultValue = "") String startDate,
    		@RequestParam(value = "endDate", required=false, defaultValue = "") String endDate,
    		@RequestParam(value = "orderby", required=false, defaultValue = "1") int orderby,
    		@RequestParam(value = "page", required=false, defaultValue = "0") int page,
    		@RequestParam(value = "pageSize", required=false, defaultValue = "10") int pageSize) {
        log.info("search getPage");
        
        ModelAndView mav = new ModelAndView();
        if (page != 0) {
        	page = page - 1;
        }
        log.info("page {}, pageSize {}", page, pageSize);
        Pageable pageable = new PageRequest(page, pageSize);
        Date sStartDate = null;
        Date sEndDate = null;
        String defaultUrlPage = "";
        if (StringUtils.isNotBlank(startDate)) {
        	sStartDate = ShoppingDateUtil.parseDateTime(startDate + " 00:00:00", "yyyy/MM/dd hh:mm:ss");
        	defaultUrlPage += "&startDate=" + startDate;
        }
        if (StringUtils.isNotBlank(endDate)) {
        	sEndDate = ShoppingDateUtil.parseDateTime(endDate + " 23:59:59", "yyyy/MM/dd hh:mm:ss");
        	defaultUrlPage += "&endDate=" + endDate;
        }
        if (StringUtils.isNotBlank(orderNo)) {
        	defaultUrlPage += "&orderNo=" + orderNo;
        }
        if (statusId != 0) {
        	defaultUrlPage += "&statusId=" + statusId;
        }
        log.info("orderNo: {}, startDate: {}, endDate: {}", orderNo, sStartDate, sEndDate);
        Page<CustomerOrder> pCustomerOrder = orderService.getFilterProduct(sStartDate, sEndDate, orderNo, statusId, orderby, pageable);
        
        
        
        defaultUrlPage += "&pageSize="+ pageSize + "&page=";
        mav.addObject("default_url_page", defaultUrlPage);
        mav.addObject("totalPages", pCustomerOrder.getTotalPages());
        mav.addObject("pageSize", pCustomerOrder.getSize());
        mav.addObject("pageNumber", pageable.getPageNumber());
        mav.addObject("pageable", pageable);
        log.info("size {}", pCustomerOrder.getContent().size());
        mav.addObject("orderList", pCustomerOrder.getContent());
        mav.setViewName("manager/order/orderList");
        return mav;
	}
        
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable int id, ModelMap map) {
		log.info("orderlist");
		CustomerOrder customerOrder = orderService.getCustomerOrder(id);
		log.info("customerOrder {}", customerOrder);
		for (OrderDetail detail : customerOrder.getOrderDetails()) {
			log.info("detail {}", detail);
			log.info("detail product {}", detail.getProduct());
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
