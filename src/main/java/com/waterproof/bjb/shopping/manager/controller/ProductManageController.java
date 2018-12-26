package com.waterproof.bjb.shopping.manager.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.entity.ProductColor;
import com.waterproof.bjb.shopping.entity.ProductColorPK;
import com.waterproof.bjb.shopping.manager.dto.DescriptionImg;
import com.waterproof.bjb.shopping.manager.dto.ProductColorDto;
import com.waterproof.bjb.shopping.service.ProductService;
import com.waterproof.bjb.shopping.service.SimpleUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager/product")
public class ProductManageController {

	@Resource
	private ProductService productService;

	@Autowired
	private Environment environment;

	@Autowired
	private SimpleUserService simpleUserService;

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public ModelAndView getProduct() {
		ModelAndView mav = new ModelAndView();

		List<Product> products = productService.getProducts();
		log.info("product: {}", products);
		mav.addObject("products", products);

		mav.setViewName("manager/product/productList");
		return mav;
	}

	// Handling file upload request
	@RequestMapping(value = "/update_fileUpload", method = { RequestMethod.POST })
	public ResponseEntity<Object> updatefileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request)
			throws IOException {
		int newId = productService.getMaxProductId();
		log.info("update_fileUpload {}", request.getSession().getAttribute("productId"));
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html", Charset.defaultCharset());
		headers.setContentType(mediaType);
		return new ResponseEntity<>(file.getOriginalFilename() + "檔案上傳完成. 商品已新增(" + newId + ")，請更新其他相關欄位。:" + newId,
				headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET })
	public ModelAndView getId(@PathVariable("id") long id, HttpServletRequest request) {
		log.info("getId id:{}", id);
		ModelAndView mav = new ModelAndView();

		Product product = productService.getProduct(id);
		log.info("product: {}", product);
		mav.addObject("product", product);
		mav.addObject("Categorys", productService.getActivateCategory());
		
		List<DescriptionImg> descriptionImgs = new ArrayList<DescriptionImg>();
		String rootPath = environment.getProperty("server.image.path");
		File f = new File(rootPath + "/image/product/" + id + "/description/");
		for (File file : f.listFiles()) {
			DescriptionImg descriptionImg = new DescriptionImg();
			log.info("getPath: {}, getName: {}", file.getPath(), file.getName());
			descriptionImg.setImagePath("/common/image/product/" + id + "/description/" + file.getName());
			descriptionImg.setFilePath(file.getPath());
			descriptionImgs.add(descriptionImg);
		}
		mav.addObject("DescriptionImgs", descriptionImgs);
		
		request.getSession().setAttribute("productId", id);
		mav.setViewName("manager/product/product-page-editor");
		return mav;
	}

	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	public ModelAndView toUpdate(@ModelAttribute Product product) {
		ModelAndView mav = new ModelAndView();
		Product p = productService.getProduct(product.getId());
		BeanUtils.copyProperties(product, p, "promotion_end_str", "img");
		p.setNewest(0);
		p.setDiscount(new BigDecimal(100));
		p.setPromotion_discount(new BigDecimal(100));
		p.setPromotion_on(BigDecimal.ZERO);
		p.setPromotion_start(new java.sql.Date(new Date().getTime()));
		p.setPromotion_end(new java.sql.Date(new Date().getTime()));
		p.setUpdate_by(simpleUserService.getUser().getUsername());
		p.setUpdated(new Timestamp(new Date().getTime()));
		productService.createProductById(p);
		log.info("product: {}", product);
		// mav.addObject("newId", newId);
		mav.addObject("product", productService.getProduct(p.getId()));
		mav.addObject("Categorys", productService.getActivateCategory());
		mav.setViewName("manager/product/product-page-editor");
		return mav;
	}
	@RequestMapping(value = "/new", method = { RequestMethod.GET })
	public ModelAndView getNew() {
		ModelAndView mav = new ModelAndView();

		// log.info("product: {}", newId);
		// mav.addObject("newId", newId);
		mav.addObject("product", new Product());
		mav.addObject("Categorys", productService.getActivateCategory());
		mav.setViewName("manager/product/product-page-new");
		return mav;
	}

	// Handling file upload request
	@RequestMapping(value = "/fileUpload", method = { RequestMethod.POST })
	public ResponseEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		int newId = productService.getMaxProductId();
		String uploadPath = environment.getProperty("server.image.path");
		String productPath = uploadPath + "/image/product/" + newId + "/";
		// 取得productId 最大

		if (!new File(productPath).isDirectory()) {
			new File(productPath).mkdirs();
		}

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html", Charset.defaultCharset());
		headers.setContentType(mediaType);
		// Save file on system
		if (!file.getOriginalFilename().isEmpty()) {
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(productPath, file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {
			return new ResponseEntity<>("上傳失敗。:", headers, HttpStatus.BAD_REQUEST);
		}

		// 這裡上傳成功開始處理相關DB部分

		Product product = new Product();
		product.setId(Long.valueOf(newId));
		product.setImg("/common/image/product/" + newId + "/" + file.getOriginalFilename());
		product.setInsert_by(simpleUserService.getUser().getUsername());
		product.setInserted(new Timestamp(new Date().getTime()));
		product.setPromotion_start(new java.sql.Date(new Date().getTime()));
		product.setPromotion_end(new java.sql.Date(new Date().getTime()));
		productService.createProductById(product);
		return new ResponseEntity<>(file.getOriginalFilename() + "檔案上傳完成. 商品已新增(" + newId + ")，請更新其他相關欄位。:" + newId,
				headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/new", method = { RequestMethod.POST })
	public ModelAndView toNew(@ModelAttribute Product product) {
		ModelAndView mav = new ModelAndView();
		Product p = productService.getProduct(product.getId());
		BeanUtils.copyProperties(product, p, "promotion_end_str", "img");
		p.setInsert_by(simpleUserService.getUser().getUsername());
		p.setInserted(new Timestamp(new Date().getTime()));
		p.setPromotion_start(new java.sql.Date(new Date().getTime()));
		p.setPromotion_end(new java.sql.Date(new Date().getTime()));
		productService.createProductById(p);
		log.info("product: {}", product);
		// mav.addObject("newId", newId);
		mav.addObject("product", new Product());

		mav.setViewName("manager/product/product-page-new");
		return mav;
	}

	@RequestMapping(value = "/edit_desc/{id}", method = { RequestMethod.GET })
	public ModelAndView toEditDescription(@PathVariable("id") long id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Product p = productService.getProduct(id);
		
		// log.info("product: {}", newId);
		// mav.addObject("newId", newId);
		mav.addObject("product", p);

		mav.setViewName("manager/product/product-page-editor-description");
		return mav;
	}
	
	@RequestMapping(value = "/update/description/{id}", method = { RequestMethod.POST })
	public ModelAndView toEditDescriptionPost(@PathVariable("id") long id, 
			@RequestParam("description") String description, HttpServletRequest request) {
		log.info("toEditDescriptionPost id:{}, description: {}", id, description);
		ModelAndView mav = new ModelAndView();
		Product p = productService.getProduct(id);
		p.setDescription(description);
		p.setUpdate_by(simpleUserService.getUser().getUsername());
		p.setUpdated(new Timestamp(new Date().getTime()));
		productService.createProductById(p);
		mav.addObject("product", p);

		mav.setViewName("manager/product/product-page-editor-description");
		return mav;
	}
	
	@RequestMapping(value = "/publish/{id}", method = { RequestMethod.GET })
	public ModelAndView toPublish(@PathVariable("id") long id, HttpServletRequest request) {
		log.info("toPublish id:{}", id);
		ModelAndView mav = new ModelAndView();

		Product p = productService.getProduct(id);
		log.info("product: {}", p);
		p.setActivate(1);
		p.setPublished(1);
		p.setUpdate_by(simpleUserService.getUser().getUsername());
		p.setUpdated(new Timestamp(new Date().getTime()));
		productService.createProductById(p);
		
		
		List<Product> products = productService.getProducts();
		log.info("product: {}", products);
		mav.addObject("products", products);

		mav.setViewName("manager/product/productList");
		return mav;
	}
	
	@RequestMapping(value = "/nonPublish/{id}", method = { RequestMethod.GET })
	public ModelAndView toNonPublish(@PathVariable("id") long id, HttpServletRequest request) {
		log.info("toPublish id:{}", id);
		ModelAndView mav = new ModelAndView();

		Product p = productService.getProduct(id);
		log.info("product: {}", p);
		p.setPublished(0);
		p.setUpdate_by(simpleUserService.getUser().getUsername());
		p.setUpdated(new Timestamp(new Date().getTime()));
		productService.createProductById(p);
		
		
		List<Product> products = productService.getProducts();
		log.info("product: {}", products);
		mav.addObject("products", products);

		mav.setViewName("manager/product/productList");
		return mav;
	}
	@RequestMapping(value = "/addColor", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProductColorDto addColor(@RequestBody ProductColorDto productColorDto,
            HttpServletRequest request) {
		log.info("productColorDto {}", productColorDto);
		ProductColor productColor = new ProductColor();
		ProductColorPK id = new ProductColorPK();
		productColor.setId(id);
		BeanUtils.copyProperties(productColorDto, productColor);
		productColor.getId().setColor(productColorDto.getColor());
		productColor.getId().setProductId(productColorDto.getProductId());
		productColor.setEName(productColorDto.getE_name());
		productColor = productService.createProductColor(productColor);
		log.info("After inset productColor: {}", productColor);
		return productColorDto;
		
	}
	
	@RequestMapping(value = "/deleteColor", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ProductColorDto deleteColor(@RequestBody ProductColorDto productColorDto,
            HttpServletRequest request) {
		log.info("productColorDto {}", productColorDto);
		productColorDto.setName(productService.getColorName(productColorDto.getProductId(), productColorDto.getColor()));
		ProductColor productColor = new ProductColor();
		ProductColorPK id = new ProductColorPK();
		productColor.setId(id);
		BeanUtils.copyProperties(productColorDto, productColor);
		productColor.getId().setColor(productColorDto.getColor());
		productColor.getId().setProductId(productColorDto.getProductId());
		
		productService.deleteProductColor(productColor);
		log.info("delete productColor: {}", productColor);
		return productColorDto;
	}
	
	// Handling file upload request
		@RequestMapping(value = "/fileUpload_Desc", method = { RequestMethod.POST })
		public ResponseEntity<Object> fileUploadDesc(@RequestParam("file") MultipartFile file, 
				HttpServletRequest request) throws IOException {
			int id = (int)request.getSession().getAttribute("productId");
			String uploadPath = environment.getProperty("server.image.path");
			String productPath = uploadPath + "/image/product/description/" + id + "/";
			// 取得productId 最大

			if (!new File(productPath).isDirectory()) {
				new File(productPath).mkdirs();
			}

			HttpHeaders headers = new HttpHeaders();
			MediaType mediaType = new MediaType("text", "html", Charset.defaultCharset());
			headers.setContentType(mediaType);
			// Save file on system
			if (!file.getOriginalFilename().isEmpty()) {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(new File(productPath, file.getOriginalFilename())));
				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
			} else {
				return new ResponseEntity<>("上傳失敗。:", headers, HttpStatus.BAD_REQUEST);
			}

			
			return new ResponseEntity<>(file.getOriginalFilename() + "檔案上傳完成.(" + id +")",
					headers, HttpStatus.OK);
		}
}
