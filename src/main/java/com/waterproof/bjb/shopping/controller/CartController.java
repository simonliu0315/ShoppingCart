package com.waterproof.bjb.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/cart")
public class CartController {

	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage() {
        log.info("index getPage");
        ModelAndView mav = new ModelAndView();
      
        mav.setViewName("cart/cart");
        return mav;
    }
	
}
