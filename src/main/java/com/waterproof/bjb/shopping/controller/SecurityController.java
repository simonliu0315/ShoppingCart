package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/")
public class SecurityController {

	@RequestMapping(value = "403", method = { RequestMethod.GET })
	public ModelAndView getPage403(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("security/403");
		return mav;
	}
	
	@RequestMapping(value = "401", method = { RequestMethod.GET })
	public ModelAndView getPage401(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("security/401");
		return mav;
	}
}
