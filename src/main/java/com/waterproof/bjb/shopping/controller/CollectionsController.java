package com.waterproof.bjb.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "collectons")
public class CollectionsController {

	@RequestMapping(value = "", method = {RequestMethod.GET})
    public ModelAndView getPage(@RequestParam(value = "page", defaultValue = "0", required = false) String page) {
        log.info("Test log");
        ModelAndView mav = new ModelAndView();
      
        mav.setViewName("collectons/index");
        return mav;
    }
	
}
