package com.waterproof.bjb.shopping.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.authentication.StatusCodes;

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
	public ModelAndView getPage401(@RequestParam Optional<String> badCredentials, 
			@RequestParam Optional<String> badCaptcha,
			@RequestParam Optional<String> accountExpired,
			@RequestParam Optional<String> locked,
			@RequestParam Optional<String> dosabled,
			Map<String,Object> param,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if(badCredentials.isPresent()) {
			param.put("status", StatusCodes.CREDENTIALS.getCode());
			param.put("message",StatusCodes.CREDENTIALS.getDescription());
		}
		if(badCaptcha.isPresent()) {
			param.put("status", StatusCodes.BADCAPTCHA.getCode());
			param.put("message",StatusCodes.BADCAPTCHA.getDescription());
		}
		mav.setViewName("security/401");
		return mav;
	}
	
	@RequestMapping(value = "404", method = { RequestMethod.GET })
	public ModelAndView getPage404(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("security/404");
		return mav;
	}
	
	@RequestMapping(value = "500", method = { RequestMethod.GET })
	public ModelAndView getPage500(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("security/404");
		return mav;
	}
}
