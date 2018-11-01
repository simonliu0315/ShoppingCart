package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "error")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView handleError(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		log.info("error code:{}", statusCode);
		if (statusCode == 401) {
			mav.setViewName("security/401");
		} else if (statusCode == 404) {
			mav.setViewName("security/404");
		} else if (statusCode == 403) {
			mav.setViewName("security/403");
		} else if (statusCode == 400) {
			mav.setViewName("security/404");
		} else {
			mav.setViewName("security/error");
		}

		return mav;
	}

	@Override
	public String getErrorPath() {
		log.info("****getErrorPath****");
		return "security/404";
	}
}
