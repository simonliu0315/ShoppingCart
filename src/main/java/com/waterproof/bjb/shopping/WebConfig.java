package com.waterproof.bjb.shopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.waterproof.bjb.shopping.interceptor.OtherInterceptor;
import com.waterproof.bjb.shopping.interceptor.UserInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired 
	private UserInterceptor userInterceptor;
	
	@Autowired
	private OtherInterceptor otherInterceptor;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("addResourceHandlers.");
		System.out.println("addResourceHandlers.");
		registry.addResourceHandler("/css/**", "/js/**", "/webjars/**", "/img/**", "/fonts/**")
		        .addResourceLocations(
		        		"classpath:/static/css/",
		        		"classpath:/static/js/",
				"classpath:/META-INF/resources/webjars/", 
				"classpath:/static/img/",
				"classpath:/static/fonts/"
				);
		 
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}

//	@Bean
//	public InternalResourceViewResolver setupViewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/ui/jsp/");
//		resolver.setSuffix(".jsp");
//		resolver.setViewClass(JstlView.class);
//		return resolver;
//	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setViewClass(ThymeleafView.class);
		resolver.setTemplateEngine(templateEngine);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}
	
	 @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(userInterceptor);
	    registry.addInterceptor(otherInterceptor);
	  }
}
