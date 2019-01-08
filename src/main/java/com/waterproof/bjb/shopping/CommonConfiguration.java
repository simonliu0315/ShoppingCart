package com.waterproof.bjb.shopping;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import com.waterproof.bjb.shopping.captcha.CaptchaVerifyServlet;
import com.waterproof.bjb.shopping.captcha.GenerateCaptchaServlet;
import com.waterproof.bjb.shopping.commons.LoggableDispatcherServlet;

@Configuration
@AutoConfigureBefore({ WebMvcAutoConfiguration.class })
public class CommonConfiguration {

	@Bean
	public ServletRegistrationBean generateCaptchaServlet() {
		return new ServletRegistrationBean(new GenerateCaptchaServlet(), "/generateCaptcha");
	}

	@Bean
	public ServletRegistrationBean captchaVerifyServlet() {
		return new ServletRegistrationBean(new CaptchaVerifyServlet(), "/verifyCaptcha");
	}

	@Bean
	public ServletRegistrationBean dispatcherRegistration() {
		return new ServletRegistrationBean(dispatcherServlet());
	}

	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		return new LoggableDispatcherServlet();
	}
}
