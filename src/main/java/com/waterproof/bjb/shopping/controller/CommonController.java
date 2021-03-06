package com.waterproof.bjb.shopping.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
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
	
	
	@RequestMapping(value = "/image/**", produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_GIF_VALUE})
	@ResponseBody
	public byte[] getImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String restOfTheUrl = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		restOfTheUrl = restOfTheUrl.replaceAll("/common", "");
		File serverFile = new File(environment.getProperty("server.image.path") + restOfTheUrl );
		log.info("requestURI: {}, imageName: {}", restOfTheUrl, serverFile.getName());
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    
        log.info("load image path: {}, isExist? {}", serverFile.getPath(), serverFile.exists());
	    return Files.readAllBytes(serverFile.toPath());
	}
}
