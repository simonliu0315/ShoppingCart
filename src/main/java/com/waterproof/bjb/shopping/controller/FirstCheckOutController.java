package com.waterproof.bjb.shopping.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.controller.dto.FirstCheckoutForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.ShippingMethod;
import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.entity.UserRolePK;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.service.dto.UserContractDto;
import com.waterproof.bjb.shopping.utils.MailUtil;
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

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService_0;
	
	@Autowired
	private MailUtil mailUtil;

	public static final String USER = "ROLE_USER";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";

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
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "userEmail", required = false) String userEmail,
			@RequestParam(value = "userTel", required = false) String userTel,
			@RequestParam(value = "userPassword", required = false) String userPassword, HttpServletRequest request) {
		log.info("checkout index getPage");
		ModelAndView mav = new ModelAndView();
		ProductInCartDto productInCartDto = (ProductInCartDto) request.getSession()
				.getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCartDto == null) {
			productInCartDto = new ProductInCartDto();
		}
		FirstCheckoutForm firstCheckoutForm = new FirstCheckoutForm(postName, email, zipCode, city, district, address,
				tel, paymentMethod, invoiceType, vatId, businessName, shippmentMethod, userName, userEmail, userTel,
				userPassword, city);
		log.info("session: {}", productInCartDto);
		mav.addObject("order", productInCartDto);
		mav.addObject("FirstCheckoutForm", firstCheckoutForm);
		mav.addObject("ShippingMethods", customerOrderService.getShipping());
		mav.addObject("bankCode", util.getBankCode());
		mav.addObject("accountNo", util.getAccountCode());
		mav.addObject("accountName", util.getAccountName());
		mav.setViewName("checkout/first_checkout");
		return mav;
	}

	@RequestMapping(value = "", method = { RequestMethod.POST })
	public ModelAndView getPagePost(@ModelAttribute FirstCheckoutForm formBean, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		log.info("getPagePost page form {}", formBean);
		if (userService_0.findById(formBean.getUserEmail()) != null) {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "帳號已存在，請輸入新帳號!");

			ProductInCartDto productInCartDto = (ProductInCartDto) request.getSession()
					.getAttribute(SessionParameter.PRODUCTS_IN_CART);
			if (productInCartDto == null) {
				productInCartDto = new ProductInCartDto();
			}
			FirstCheckoutForm firstCheckoutForm = new FirstCheckoutForm(formBean.getPostName(), formBean.getEmail(),
					formBean.getZipCode(), formBean.getCounty(), formBean.getDistrict(), formBean.getAddress(),
					formBean.getTel(), formBean.getPaymentMethod(), formBean.getInvoiceType(), formBean.getVatId(),
					formBean.getBusinessName(), formBean.getShippmentMethod(), formBean.getUserName(),
					formBean.getUserEmail(), formBean.getUserTel(), formBean.getUserPassword(), formBean.getCity());
			log.info("session: {}", productInCartDto);
			mav.addObject("order", productInCartDto);
			mav.addObject("FirstCheckoutForm", firstCheckoutForm);
			mav.addObject("ShippingMethods", customerOrderService.getShipping());
			mav.addObject("bankCode", util.getBankCode());
			mav.addObject("accountNo", util.getAccountCode());
			mav.addObject("accountName", util.getAccountName());

			mav.setViewName("checkout/first_checkout");
			return mav;
		}

		// 先新增使用者 在處理訂單
		//注意這裡的username 是 email
		User u = new User();
		u.setUsername(formBean.getUserEmail());
		u.setCName(formBean.getUserName());
		u.setEmail(formBean.getUserEmail());
		u.setPassword(formBean.getUserPassword());
		u.setMobile(formBean.getUserTel());
		u.setStatus(1);

		UserRole role = new UserRole();
		UserRolePK id = new UserRolePK();
		role.setId(id);
		id.setUsername(u.getUsername());
		id.setRole(ANONYMOUS);
		role.setStatus(1);
		List<UserRole> userRoles = new ArrayList<UserRole>();
		userRoles.add(role);

		role = new UserRole();
		id = new UserRolePK();
		role.setId(id);
		id.setUsername(u.getUsername());
		id.setRole(USER);
		role.setStatus(1);
		userRoles.add(role);
		userService_0.insertByUser(u, userRoles);

		
		ProductInCartDto productInCartDto = (ProductInCartDto) request.getSession()
				.getAttribute(SessionParameter.PRODUCTS_IN_CART);
		if (productInCartDto == null) {
			log.info("productInCartDto is null set NEW one.");
			productInCartDto = new ProductInCartDto();
		}
		productInCartDto.setPaymentMethod(formBean.getPaymentMethod());
		String username = formBean.getUserEmail();
		if (StringUtils.isBlank(username)) {
			mav.setViewName("/");
			return mav;
		}
		if (formBean.getPaymentMethod() == 0) {
			mav.addObject("order", productInCartDto);
			mav.addObject("CheckoutForm", new CheckoutForm());
			mav.setViewName("checkout/first_checkout");
			return mav;
		}
		productInCartDto.setUserId(username);
		log.info("session: {}", productInCartDto);
		UserContractDto userContractDto = new UserContractDto();
		BeanUtils.copyProperties(formBean, userContractDto);
		userContractDto.setUsername(username);
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
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
				formBean.getUserPassword(),
				Arrays.asList(new SimpleGrantedAuthority(ANONYMOUS), new SimpleGrantedAuthority(USER)));

		authToken.setDetails(new WebAuthenticationDetails(request));

		Authentication authentication = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return mav;
	}
}
