package com.waterproof.bjb.shopping;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.waterproof.bjb.shopping.captcha.CaptchaVerifyServlet;
import com.waterproof.bjb.shopping.captcha.GenerateCaptchaServlet;

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

}
