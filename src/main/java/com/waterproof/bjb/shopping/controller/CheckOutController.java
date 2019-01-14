package com.waterproof.bjb.shopping.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.entity.ShippingMethod;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.service.dto.UserContractDto;
import com.waterproof.bjb.shopping.utils.MailUtil;
import com.waterproof.bjb.shopping.utils.NotifyUtil;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/checkout")
public class CheckOutController {

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private SimpleUserService userService;

	@Autowired
	private NotifyUtil util;

	@Autowired
	private Environment environment;

	@Autowired
	private MailUtil mailUtil;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView getPage(@RequestParam(value = "postName", required = false) String postName,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "zipCode", required = false) String zipCode,
			@RequestParam(value = "city", required = false) String city,
			@RequestParam(value = "district", required = false) String district,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "tel", required = false) String tel,
			@RequestParam(value = "paymentMethod", required = false, defaultValue = "1") int paymentMethod,
			@RequestParam(value = "invoiceType", required = false, defaultValue = "1") int invoiceType,
			@RequestParam(value = "vatId", required = false, defaultValue = "") String vatId,
			@RequestParam(value = "businessName", required = false, defaultValue = "") String businessName,
			@RequestParam(value = "shippmentMethod", required = false, defaultValue = "1") int shippmentMethod,
			HttpServletRequest request) {
		log.info("checkout index getPage");
		ModelAndView mav = new ModelAndView();
		ProductInCartDto productInCartDto = (ProductInCartDto) request.getSession()
				.getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCartDto == null) {
			productInCartDto = new ProductInCartDto();
		}
		CheckoutForm checkoutForm = new CheckoutForm(postName, email, zipCode, city, district, address, tel,
				paymentMethod, invoiceType, vatId, businessName, shippmentMethod);
		log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("CheckoutForm", checkoutForm);
		mav.addObject("ShippingMethods", customerOrderService.getShipping());
		mav.addObject("bankCode", util.getBankCode());
		mav.addObject("accountNo", util.getAccountCode());
		mav.addObject("accountName", util.getAccountName());
		mav.setViewName("checkout/checkout");
		return mav;
	}

	@RequestMapping(value = "", method = { RequestMethod.POST })
	public ModelAndView getPagePost(@ModelAttribute CheckoutForm formBean, HttpServletRequest request)
			throws Exception {
		log.info("getPagePost page form {}", formBean);
		ModelAndView mav = new ModelAndView();
		ProductInCartDto productInCartDto = (ProductInCartDto) request.getSession()
				.getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCartDto == null) {
			productInCartDto = new ProductInCartDto();
		}
		productInCartDto.setPaymentMethod(formBean.getPaymentMethod());
		String username = userService.getUser().getUsername();
		if (StringUtils.isBlank(username)) {
			mav.setViewName("/");
			return mav;
		}
		if (formBean.getPaymentMethod() == 0) {
			mav.addObject("order", productInCartDto);
			mav.addObject("CheckoutForm", new CheckoutForm());
			mav.setViewName("checkout/checkout");
			return mav;
		}
		productInCartDto.setUserId(username);
		log.info("session: {}", productInCartDto);
		UserContractDto userContractDto = new UserContractDto();
		BeanUtils.copyProperties(formBean, userContractDto);

		log.info("login user {}", userService.getUser());
		log.info("userContractDto {}", userContractDto);
		for (ShippingMethod shippingMethod : customerOrderService.getShipping()) {
			productInCartDto.setShipment(new BigDecimal(shippingMethod.getShipping()));
		}
		String orderNumber = customerOrderService.saveToOrder(userContractDto, productInCartDto, username);
		request.getSession().setAttribute(SessionParameter.PRODUCTS_IN_CART, null);
		ProductInCartDto dto = customerOrderService.queryOrderByNo(orderNumber);
		log.info("dto :{}", dto);
		List<ProductInCartDto> list = new ArrayList<ProductInCartDto>();
		list.add(dto);
		mav.addObject("orderNumber", orderNumber);
		mav.addObject("orders", list);
		mav.addObject("fromPage", "checkout");
		mav.addObject("tip", util.getBankTransferInfo());
		mav.addObject("bankCode", util.getBankCode());
		mav.addObject("accountNo", util.getAccountCode());
		mav.addObject("accountName", util.getAccountName());
		;
		mav.addObject("expiryDate", DateUtils.addDays(new Date(), 7));
		mav.setViewName("order/list");

		// 寄信
		try {
			mailUtil.sendByGmail("築城國際網路訂購中心-購物清單!",
					MailUtil.getCustomerOrderMailContent(username, orderNumber, dto).toString(), formBean.getEmail());
		} catch (Exception e) {
			log.error("{}", e);
		}
		return mav;
	}
}
