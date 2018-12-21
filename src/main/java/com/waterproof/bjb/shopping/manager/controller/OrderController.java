package com.waterproof.bjb.shopping.manager.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.util.WebUtils;

import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderDetail;
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
    		@RequestParam(value = "pageSize", required=false, defaultValue = "10") int pageSize,
    		HttpServletRequest request) {
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
        log.info("orderNo: {}, statusId: {}, startDate: {}, endDate: {}", orderNo, statusId, sStartDate, sEndDate);
        Page<CustomerOrder> pCustomerOrder = orderService.getFilterProduct(sStartDate, sEndDate, orderNo, statusId, orderby, pageable);
        
        
        defaultUrlPage += "&pageSize="+ pageSize + "&page=";
        mav.addObject("default_url_page", defaultUrlPage);
        mav.addObject("totalPages", pCustomerOrder.getTotalPages());
        mav.addObject("pageSize", pCustomerOrder.getSize());
        mav.addObject("pageNumber", pageable.getPageNumber());
        mav.addObject("pageable", pageable);
        log.info("size {}", pCustomerOrder.getContent().size());
        mav.addObject("orderList", pCustomerOrder.getContent());
        
        log.info("SET session ExportOrder");
        WebUtils.setSessionAttribute(request, "ExportOrder", orderService.getFilterProduct(sStartDate, sEndDate, orderNo, statusId, orderby));
        mav.setViewName("manager/order/orderList");
        return mav;
	}
        
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String getOrder(@PathVariable int id, ModelMap map) {
		log.info("orderlist");
		CustomerOrder customerOrder = orderService.getCustomerOrder(id);
		log.info("customerOrder {}", customerOrder);
		for (OrderDetail detail : customerOrder.getOrderDetails()) {
			log.info("detail {}", detail);
			log.info("detail product {}", detail.getProduct());
		}
		if (customerOrder.getShipping() == null) {
			customerOrder.setShipping(BigDecimal.ZERO);
		}
		map.addAttribute("order", customerOrder);
		map.addAttribute("action", "update");
		return "manager/order/orderForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateOrder(@RequestParam("orderNo") String orderNo, 
			@RequestParam("statusId") int statusId, ModelMap map) {
		log.info("updateOrder orderNo {}, statusId {}", orderNo, statusId);
		CustomerOrder customerOrder = orderService.getCustomerOrder(orderNo);
		log.info("1. customerOrder {}", customerOrder);
		orderService.updateCustomerOrderStatus(customerOrder.getId(), statusId);
//		orderService.updateCustomerOrder(statusId, orderNo);
		customerOrder = orderService.getCustomerOrder(customerOrder.getId());
		log.info("2. customerOrder {}", customerOrder);
		for (OrderDetail detail : customerOrder.getOrderDetails()) {
			log.info("detail {}", detail);
			log.info("detail product {}", detail.getProduct());
		}
		map.addAttribute("order", customerOrder);
		map.addAttribute("action", "update");
		map.addAttribute("msgType", "alert alert-info alert-dismissible");
		map.addAttribute("msg", "狀態[" + customerOrder.getOrderStatus().getDescription() + "]修改完成。");
		return "manager/order/orderForm";
	}

	@RequestMapping(value = "/reportCustom", method = RequestMethod.GET)
	public ModelAndView getCustomExcel(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<CustomerOrder> customerOrderList = (List)WebUtils.getSessionAttribute(request, "ExportOrder");
		mav.addObject("CustomerOrderList", customerOrderList);
		log.info("reportCustom size:{}", customerOrderList.size());
		mav.setView(new ExcelReportCustomView());
		mav.addObject("UserService", userService);
		return mav;
		//return new ModelAndView(new ExcelReportCustomView(), );
	}
	@RequestMapping(value = "/reportOrder", method = RequestMethod.GET)
	public ModelAndView getOrderExcel(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<CustomerOrder> customerOrderList = (List)WebUtils.getSessionAttribute(request, "ExportOrder");
		mav.addObject("CustomerOrderList", customerOrderList);
		log.info("reportCustom size:{}", customerOrderList.size());
		mav.setView(new ExcelReportOrderView());
		mav.addObject("UserService", userService);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteOrder(@PathVariable int id, ModelMap map) {
		log.info("deleteOrder id {}", id);
		orderService.updateCustomerOrderStatus(id, 7);
		
		return new ModelAndView("redirect:/manager/order/search");
	}
}
