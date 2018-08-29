package com.waterproof.bjb.shopping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

		    @Override
		    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		        registry.addResourceHandler("/js/**").addResourceLocations("/static/js");
		        registry.addResourceHandler("/css/**").addResourceLocations("/static/css");
		    }
		    
		    @Override
		    public void addViewControllers(ViewControllerRegistry registry) {
		        registry.addViewController("/").setViewName("index");
		        registry.addViewController("/login").setViewName("login");
		    }
		    
		    @Bean
		    public InternalResourceViewResolver setupViewResolver()  {
		        InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
		        resolver.setPrefix ("/ui/jsp/");
		        resolver.setSuffix (".jsp");
		        resolver.setViewClass (JstlView.class);
		        return resolver;
		    }
}
