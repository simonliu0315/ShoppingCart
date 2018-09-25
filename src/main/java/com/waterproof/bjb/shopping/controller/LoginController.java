package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.controller.dto.LoginForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
	
	@RequestMapping(value = "", method = {RequestMethod.POST})
    public void getPagePost(@ModelAttribute LoginForm formBean, HttpServletRequest request) {
        log.info("getPagePost page form {}", formBean);
        
	}
}
