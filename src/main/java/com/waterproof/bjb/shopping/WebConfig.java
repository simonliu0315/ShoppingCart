package com.waterproof.bjb.shopping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.octo.captcha.CaptchaException;
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
		registry.addResourceHandler("/css/**", "/js/**", "/webjars/**", "/img/**", "/fonts/**").addResourceLocations(
				"classpath:/static/css/", "classpath:/static/js/", "classpath:/META-INF/resources/webjars/",
				"classpath:/static/img/", "classpath:/static/fonts/");

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
	}

	// @Bean
	// public InternalResourceViewResolver setupViewResolver() {
	// InternalResourceViewResolver resolver = new
	// InternalResourceViewResolver();
	// resolver.setPrefix("/ui/jsp/");
	// resolver.setSuffix(".jsp");
	// resolver.setViewClass(JstlView.class);
	// return resolver;
	// }

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

	
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        
	    	@Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {

	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
	            container.addErrorPages(error404Page);
	            
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404");
	            container.addErrorPages(error500Page);
	            
	            ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/404");
	            container.addErrorPages(error405Page);

	        }
	    };
	}
}
