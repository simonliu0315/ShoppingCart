package com.waterproof.bjb.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GreetingController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(100); // simulated delay
		log.info("greeting {}", message.getName());
		
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
	@RequestMapping(value = "/websocket", method = {RequestMethod.GET})
	public ModelAndView getPage(HttpServletRequest request) {
		log.info("websocket page");
		ModelAndView mav = new ModelAndView();
        mav.setViewName("websocket");
        return mav;
    }
}
