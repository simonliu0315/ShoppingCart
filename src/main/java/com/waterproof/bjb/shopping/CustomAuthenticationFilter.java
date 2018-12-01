package com.waterproof.bjb.shopping;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.octo.captcha.CaptchaException;
import com.waterproof.bjb.shopping.authentication.BadCaptchaException;
import com.waterproof.bjb.shopping.captcha.CaptchaVerifyService;
import com.waterproof.bjb.shopping.controller.IndexController;
import com.waterproof.bjb.shopping.utils.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Resource(name = "userDetailService")
	private UserDetailsService userDetailsService;

	@Autowired
	private CaptchaVerifyService captchaVerifyService;

	@Resource
	private AuthenticationFailureHandler authenticationFailureHandler;

	private boolean postOnly = true;
	
	public CustomAuthenticationFilter() {
		AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/login", "POST");
		this.setRequiresAuthenticationRequestMatcher(requestMatcher);
		this.setAuthenticationManager(getAuthenticationManager());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		setAuthenticationFailureHandler(authenticationFailureHandler);

		log.info("=====attemptAuthentication====");
		// 只接受POST方式傳遞的數據
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("不支持非POST方式的請求!");
		}
		// 驗證碼驗證
		String requestCaptcha = request.getParameter("verifyCode");
		log.info("requestCaptcha: {}", requestCaptcha);
		if (StringUtils.isEmpty(requestCaptcha)) {
			throw new BadCaptchaException("驗證碼不能為空!");
		}
		if (!captchaVerifyService.isVerify(request.getSession().getId(), requestCaptcha)) {

			throw new BadCaptchaException("驗證碼錯誤!");
		}

		// 判斷登陸次數及上限時間
//		String username = obtainUsername(request);
//		String password = obtainPassword(request);
//		if (username == null) {
//			username = "";
//		}
//
//		if (password == null) {
//			password = "";
//		}
//		username = username.trim();
		//password = PasswordUtil.getPassword(password);
		
//		String hashed = "$2a$10$NnlqpBH.dJZLqG//0IWoG.W8IowSknbv4yIoW0rHpvbQRrHg3LeFC";
//		hashed = "$2a$10$NnlqpBH.dJZLqG//0IWoG.SEJAyvXqEn.VvxadiyuMx1ImrCQ1D1q";
//		hashed= "$2a$10$NnlqpBH.dJZLqG//0IWoG.";
//		String plaintext = "700315";
//		String A = BCrypt.hashpw(plaintext, hashed);
//		String B = BCrypt.hashpw(plaintext, A);
//		log.info("hashed: {}",  A);
//		log.info("password: {}",  B);
//		log.info("check A B ======{}", equalsNoEarlyReturn(A, B));
//		log.info("check ======{}", equalsNoEarlyReturn(hashed, BCrypt.hashpw(plaintext, hashed)));
//		
//		
		
		
		
		
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//				username, password);
//		
//		// Allow subclasses to set the "details" property
//		setDetails(request, authRequest);
//		//UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//		return this.getAuthenticationManager().authenticate(authRequest);
		return super.attemptAuthentication(request, response);
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	static boolean equalsNoEarlyReturn(String a, String b) {
		char[] caa = a.toCharArray();
		char[] cab = b.toCharArray();

		if (caa.length != cab.length) {
			return false;
		}

		byte ret = 0;
		for (int i = 0; i < caa.length; i++) {
			ret |= caa[i] ^ cab[i];
		}
		return ret == 0;
	}
}
