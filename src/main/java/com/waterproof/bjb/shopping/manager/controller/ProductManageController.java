package com.waterproof.bjb.shopping.manager.controller;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/product")
public class ProductManageController {

	@Resource
	private ProductService productService;
	
	@Autowired
	private Environment environment;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView getProduct() {
		ModelAndView mav = new ModelAndView();

		List<Product> products = productService.getProducts();
		log.info("product: {}", products);
		mav.addObject("products", products);

		mav.setViewName("manager/product/productList");
		return mav;
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET })
	public ModelAndView getId(@PathVariable("id") long id) {
		log.info("getId:{}", id);
		ModelAndView mav = new ModelAndView();

		Product product = productService.getProduct(id);
		log.info("product: {}", product);
		mav.addObject("product", product);

		mav.setViewName("manager/product/product-page");
		return mav;
	}
	
	@RequestMapping(value = "/new", method = { RequestMethod.GET })
	public ModelAndView getNew() {
		ModelAndView mav = new ModelAndView();

		int newId = productService.getMaxProductId();
		log.info("product: {}", newId);
		mav.addObject("newId", newId);
		mav.addObject("product", new Product());

		mav.setViewName("manager/product/product-page-new");
		return mav;
	}

	// Handling file upload request
	@RequestMapping(value = "/fileUpload", method = { RequestMethod.POST })
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
         String uploadPath = environment.getProperty("server.image.path");
		 HttpHeaders headers = new HttpHeaders();     
         MediaType mediaType=new MediaType("text","html",Charset.defaultCharset());     
         headers.setContentType(mediaType);     
		// Save file on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(uploadPath + "/image/product", file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {
			return new ResponseEntity<>("上傳失敗.", headers, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(file.getOriginalFilename() + "檔案上傳完成.", headers, HttpStatus.OK);
	}
}
