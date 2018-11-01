package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.controller.dto.UserForm;
import com.waterproof.bjb.shopping.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private UserService userservice;
	
	@RequestMapping(value = "/createUser", method = {RequestMethod.GET })
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
		mav.setViewName("member/create_user");
		return mav;
	}
}
