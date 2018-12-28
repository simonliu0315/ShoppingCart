package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.commons.SessionParameter;
import com.waterproof.bjb.shopping.controller.dto.CheckoutForm;
import com.waterproof.bjb.shopping.controller.dto.LoginForm;
import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.service.SimpleUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private SimpleUserService simpleUserService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(HttpServletRequest request) {
        log.info("checkout index getPage");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        log.info("=====> username(anonymousUser)? {}",simpleUserService.getUser().getUsername());
        if (!StringUtils.equals("anonymousUser", simpleUserService.getUser().getUsername())) {
        	log.info("User is already login. goto redirect:/index");
        	mav.setViewName("redirect:/");
        }
        return mav;
    }
	
	@RequestMapping(value = "", method = {RequestMethod.POST})
    public void getPagePost(@ModelAttribute LoginForm formBean, HttpServletRequest request) {
        log.info("getPagePost page form {}", formBean);
        
	}
}
