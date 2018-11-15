package com.waterproof.bjb.shopping.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/common")
public class CommonController {

	@Autowired
	private Environment environment;
	
	
	@RequestMapping(value = "/image/**")
	@ResponseBody
	public byte[] getImage(HttpServletRequest request) throws IOException {
		String restOfTheUrl = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		restOfTheUrl = restOfTheUrl.replaceAll("/common", "");
		
		log.info("requestURI: {}, imageName: {}", restOfTheUrl);
	    
	    File serverFile = new File(environment.getProperty("server.image.path") + restOfTheUrl );
        log.info("load image path: {}", serverFile.getPath());
	    return Files.readAllBytes(serverFile.toPath());
	}
}
