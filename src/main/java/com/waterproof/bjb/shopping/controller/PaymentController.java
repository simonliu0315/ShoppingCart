package com.waterproof.bjb.shopping.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.node.TextNode;
import com.hitrust.b2ctoolkit.b2cpay.B2CPayAuth;
import com.hitrust.b2ctoolkit.b2cpay.B2CPayUpdate;
import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.dto.ProductDto;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.manager.service.OrderService;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "", method = {RequestMethod.POST})
    public ModelAndView getPaymentPost(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String orderNo = request.getParameter("orderNo");
		log.info("orderNo: {}, {}", orderNo, request.getParameter("orderNo"));
		ProductInCartDto dto = customerOrderService.queryOrderByNo(orderNo);
		if (dto.getPaymentMethod() == 2) {
			mav.addObject("orderNumber", orderNo);
			mav.setViewName("order/atm");
			return mav;
		}
		B2CPayAuth auth = paymentService.creditcardAuth(orderNo, "Test", dto.getTotalAmt().toPlainString());
		
		if (auth.getRetCode().equals("00")) {
			log.info("Token: {}", auth.getToken());
			mav.setViewName("order/payment");
			mav.addObject("url", auth.getToken());
			mav.addObject("msgType", "alert alert-info alert-dismissible");
			mav.addObject("msg", "導向刷卡頁中...");
		} else {
			log.info("RetCode: {}", auth.getRetCode());
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "發生錯誤，請與發卡銀行聯絡(" + auth.getRetCode()+ ")");			
	        log.info("dto :{}", dto);
	        List<ProductInCartDto> list = new ArrayList<ProductInCartDto>();
	        list.add(dto);
	        mav.addObject("orderNumber", orderNo);
			mav.addObject("orders", list);
	        
			mav.setViewName("order/list");
		}
		
		return mav;
		
	}
	@RequestMapping(value = "/payment_receive/creditcard", method = { RequestMethod.GET })
	public ModelAndView getPage(@RequestParam(value = "type", required=true, defaultValue = "") String type,
			@RequestParam(value = "ordernumber", required=true, defaultValue = "") String ordernumber,
			@RequestParam(value = "retcode", required=true, defaultValue = "") String retcode) {
		ModelAndView mav = new ModelAndView();
		log.info("creditcard getPage");
		log.info("type: {}, ordernumber: {}, retcode: {}.", type, ordernumber, retcode);
		CustomerOrder order = orderService.getCustomerOrder(ordernumber);
		if (StringUtils.equals(retcode, "00")) {
			if (order.getStatusId() < 3) {
				order.setStatusId(2);	
			}
			mav.addObject("msgType", "alert alert-info alert-dismissible");
			mav.addObject("msg", "刷卡完成，已通知相關人員，請等待出貨");
		} else {
			order.setStatusId(1);
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "發生錯誤，請與發卡銀行聯絡(" + retcode+ ")");	
		}
		order = orderService.updateCustomerOrderStatus(order.getId(), order.getStatusId());
		log.info("order {}", order);
		
		ProductInCartDto dto = customerOrderService.queryOrderByNo(ordernumber);
        List<ProductInCartDto> list = new ArrayList<ProductInCartDto>();
        list.add(dto);
        mav.addObject("orderNumber", ordernumber);
		mav.addObject("orders", list);
        mav.setViewName("order/list");
        return mav;
	}
	
	@RequestMapping(value = "/payment_receive/creditcard", method = { RequestMethod.POST })
	public String getPagePost(HttpServletRequest request) {
		String key = request.getParameter("KEY");
		String mac = request.getParameter("MAC");
		String cipher = request.getParameter("CIPHER");
		log.info("creditcard getPagePost");
		log.info("KEY: {}, MAC: {}, CIPHER: {}", key, mac, cipher);
		B2CPayUpdate update = paymentService.creditcardPayUpdate(key, mac, cipher);
		if (StringUtils.equals(update.getRetCode(), "00")) {
			if(StringUtils.equals(update.getType(), "1")) {
				CustomerOrder order = orderService.getCustomerOrder(update.getOrderNo());
				order = orderService.updateCustomerOrderStatus(order.getId(), 3);
			}
		} else {
			log.error("網際威信第二次回傳查詢交易錯誤{}", update.getRetCode());
			CustomerOrder order = orderService.getCustomerOrder(update.getOrderNo());
			order = orderService.updateCustomerOrderStatus(order.getId(), 1);
		}
		return "order/blank";
	}
	
	@RequestMapping(value = "/infoPayment", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Boolean infoPayment(
    		@RequestBody String orderNoJson, HttpServletRequest request) {
		JSONObject jsonObj=new JSONObject(orderNoJson);
		String orderNo=jsonObj.getString("orderNo");
		log.info("orderNo: {}", orderNo);
		if (StringUtils.isNotBlank(orderNo)) {
			CustomerOrder order = orderService.getCustomerOrder(orderNo);
			log.info("order: {}", orderNo);
			if (order != null) {
				
				order = orderService.updateCustomerOrderStatus(order.getId(), 2);
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
		
	}
}
