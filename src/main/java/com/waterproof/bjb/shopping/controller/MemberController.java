package com.waterproof.bjb.shopping.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hitrust.b2ctoolkit.b2cpay.B2CPayAuth;
import com.hitrust.b2ctoolkit.util.HiMerchant;
import com.hitrust.b2ctoolkit.util.HiServer3;
import com.hitrust.b2ctoolkit.util.ToolkitException;
import com.waterproof.bjb.shopping.authentication.BadCaptchaException;
import com.waterproof.bjb.shopping.captcha.CaptchaVerifyService;
import com.waterproof.bjb.shopping.controller.dto.UserForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.entity.UserRolePK;
import com.waterproof.bjb.shopping.service.CustomerOrderService;
import com.waterproof.bjb.shopping.service.SimpleUserService;
import com.waterproof.bjb.shopping.service.UserService;
import com.waterproof.bjb.shopping.utils.MailUtil;
import com.waterproof.bjb.shopping.utils.NotifyUtil;
import com.waterproof.bjb.shopping.utils.PasswordUtil;
import com.waterproof.bjb.shopping.utils.ShoppingDateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private UserService userservice;

	@Autowired
	private MailUtil mailUtil;

	@Autowired
	private Environment environment;

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private SimpleUserService simpleUserService;

	@Autowired
	private CaptchaVerifyService captchaVerifyService;

	@Autowired
	private NotifyUtil util;

	@RequestMapping(value = "/createUser", method = { RequestMethod.GET })
	public ModelAndView getPage(HttpServletRequest request) {
		log.info("getPagePost page form {}");
		ModelAndView mav = new ModelAndView();
		mav.addObject("UserForm", new UserForm());
		mav.setViewName("member/create_user");

		return mav;
	}

	@RequestMapping(value = "/createUser", method = { RequestMethod.POST })
	public ModelAndView getPagePost(@ModelAttribute UserForm formBean, HttpServletRequest request) {
		log.info("getPagePost page form {}", formBean);
		ModelAndView mav = new ModelAndView();
		// 驗證碼驗證
		String requestCaptcha = request.getParameter("verifyCode");
		log.info("requestCaptcha: {}", requestCaptcha);
		if (StringUtils.isEmpty(requestCaptcha)) {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "驗證碼不能為空!");
			mav.setViewName("member/create_user");
			return mav;
		}
		if (!captchaVerifyService.isVerify(request.getSession().getId(), requestCaptcha)) {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "驗證碼錯誤!");
			mav.setViewName("member/create_user");
			return mav;
		}

		User u = userservice.findById(formBean.getUsername());
		if (u == null) {
			String verifyCode = PasswordUtil
					.get_SHA_512_SecurePassword(DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"), "1234567");
			User user = new User();
			BeanUtils.copyProperties(formBean, user);
			user.setBirthday(ShoppingDateUtil.parseDateTime(formBean.getBirthday(), "yyyy/MM/dd"));
			user.setVerifyCode(verifyCode);
			List<UserRole> userRoles = new ArrayList<UserRole>();
			UserRole role = new UserRole();
			UserRolePK roleId = new UserRolePK();
			roleId.setRole("ROLE_USER");
			roleId.setUsername(user.getUsername());
			role.setId(roleId);
			role.setStatus(1);
			userRoles.add(role);

			role = new UserRole();
			roleId = new UserRolePK();
			roleId.setRole("ROLE_ANONYMOUS");
			roleId.setUsername(user.getUsername());
			role.setId(roleId);
			role.setStatus(1);
			userRoles.add(role);
			// user.setUserRoles(userRoles);
			userservice.insertByUser(user, userRoles);
			mav.addObject("msgType", "alert alert-info alert-dismissible");
			mav.addObject("msg", "帳戶" + user.getUsername() + "新增成功, 我們已經發送認證信至您的email信箱，請確認認證信並啟用帳號。");
			String siteHost = environment.getProperty("spring.web.host");
			mailUtil.sendByGmail(
					"會員認證信", "親愛的用戶您好：<br/>請點選底下連結請用您的帳戶，謝謝。<a href='" + siteHost + "/member/verify/user?code="
							+ verifyCode + "'>" + siteHost + "/member/verify/user?code=" + verifyCode + "</a>",
					user.getEmail());
			mav.setViewName("login");
		} else {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "新增失敗，原因帳戶已存在");
			mav.setViewName("member/create_user");
		}
		return mav;
	}

	@RequestMapping(value = "/verify/user", method = { RequestMethod.GET })
	public ModelAndView verify(@RequestParam("code") String code) {
		log.info("getPagePost page form {}");
		ModelAndView mav = new ModelAndView();
		List<User> users = userservice.findByVerifyCode(code);
		if (CollectionUtils.isNotEmpty(users)) {
			User user = users.get(0);
			user.setStatus(1);
			userservice.update(user);
			mav.addObject("msgType", "alert alert-info alert-dismissible");
			mav.addObject("msg", "帳戶" + user.getUsername() + "啟用成功.");
		} else {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "啟用失敗，請洽管理員或重新認證.");
		}
		mav.addObject("UserForm", new UserForm());
		mav.setViewName("member/activate");
		return mav;
	}

	@RequestMapping(value = "/order/list", method = { RequestMethod.GET })
	public ModelAndView orderlist() {
		log.info("orderlist");
		ModelAndView mav = new ModelAndView();
		String username = simpleUserService.getUser().getUsername();
		List<ProductInCartDto> productInCartDtos = customerOrderService.queryOrder(username);
		mav.addObject("orders", productInCartDtos);
		mav.addObject("tip", util.getBankTransferInfo());
		mav.addObject("fromPage", "member");
		mav.setViewName("order/list");
		return mav;
	}

	@RequestMapping(value = "/myAccount", method = { RequestMethod.GET })
	public ModelAndView myAccount() {
		ModelAndView mav = new ModelAndView();
		User u = simpleUserService.getUser();
		if(u.getBirthday() != null) {
		    u.setBirthdayStr(ShoppingDateUtil.formatDate(u.getBirthday(), "yyyy/MM/dd"));
		}
		mav.addObject("user", u);
		mav.setViewName("member/user");
		return mav;
	}

	@RequestMapping(value = "/modifyUser", method = { RequestMethod.POST })
	public ModelAndView getModifyPagePost(@ModelAttribute UserForm formBean, HttpServletRequest request) {
		log.info("getPagePost page form {}", formBean);
		
		ModelAndView mav = new ModelAndView();
		// 驗證碼驗證
		String requestCaptcha = request.getParameter("verifyCode");
		log.info("requestCaptcha: {}", requestCaptcha);
		if (StringUtils.isEmpty(requestCaptcha)) {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "驗證碼不能為空!");
			mav.addObject("user", simpleUserService.getUser());
			mav.setViewName("member/user");
			return mav;
		}
		if (!captchaVerifyService.isVerify(request.getSession().getId(), requestCaptcha)) {
			mav.addObject("msgType", "alert alert-danger alert-dismissible");
			mav.addObject("msg", "驗證碼錯誤!");
			mav.addObject("user", simpleUserService.getUser());
			mav.setViewName("member/user");
			return mav;
		}
		User user = new User();
		BeanUtils.copyProperties(formBean, user);
		User u = userservice.findById(simpleUserService.getUser().getUsername());
		if (u != null) {
			user.setBirthday(ShoppingDateUtil.parseDateTime(formBean.getBirthday(), "yyyy/MM/dd"));
			user.setStatus(1);
			userservice.update(user);
		}
		mav.addObject("msgType", "alert alert-info alert-dismissible");
		mav.addObject("msg", "修改完成!");
		mav.addObject("user", simpleUserService.getUser());
		mav.setViewName("member/user");
		return mav;
	}
}
