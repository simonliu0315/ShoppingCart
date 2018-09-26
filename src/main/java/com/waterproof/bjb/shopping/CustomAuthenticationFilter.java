package com.waterproof.bjb.shopping;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.waterproof.bjb.shopping.captcha.CaptchaVerifyService;
import com.waterproof.bjb.shopping.controller.IndexController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Resource(name="userDetailService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CaptchaVerifyService captchaVerifyService;
	
	@Override
	  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	      throws AuthenticationException {
		log.info("=====attemptAuthentication====");
	    // 只接受POST方式傳遞的數據
	    if (!"POST".equals(request.getMethod()))
	      throw new AuthenticationServiceException("不支持非POST方式的請求!");
	    // 驗證碼驗證
	    String requestCaptcha = request.getParameter("verifyCode");
	    log.info("requestCaptcha: {}", requestCaptcha);
	   if (StringUtils.isEmpty(requestCaptcha))
	      throw new AuthenticationServiceException("驗證碼不能為空!");
	    if (!captchaVerifyService.isVerify(request.getSession().getId(), requestCaptcha)) {
	      throw new AuthenticationServiceException("驗證碼錯誤!");
	    }

	    // 判斷登陸次數及上限時間
	    String username = obtainUsername(request);
	    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	    
	    return super.attemptAuthentication(request, response);
	  }
	
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
}
