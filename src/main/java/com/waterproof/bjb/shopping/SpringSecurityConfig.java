package com.waterproof.bjb.shopping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterproof.bjb.shopping.authentication.BadCaptchaException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {

	public static final String ADMIN = "ADMIN";
	public static final String USER = "USER";
	public static final String ANONYMOUS = "ANONYMOUS";
	
	@Resource(name="userDetailService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomAuthenticationFilter customAuthenticationFilter;
	
	private final ObjectMapper objectMapper = null;

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("****configure****");
		customAuthenticationFilter.setAuthenticationManager(authenticationManager());
		customAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
		http
		        .csrf()
		        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"))
		    .and()
		        .authorizeRequests()
				.antMatchers("/").hasAnyRole(ANONYMOUS)				
				.antMatchers("/checkout/**").hasAnyRole(USER)
				.antMatchers("/member/order/**").hasAnyRole(USER)
				.antMatchers("/manager/**").hasAnyRole(ADMIN)
			.and()
				.formLogin()
				.defaultSuccessUrl("/")
				.loginPage("/login")
				.failureHandler(this::loginFailureHandler)
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
				.permitAll()
			.and()
			    .exceptionHandling().accessDeniedPage("/403");
		
		http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		log.info("call configureGlobal.");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/css/**");
	     web.ignoring().antMatchers("/js/**");
	     web.ignoring().antMatchers("/fonts/**");
	     web.ignoring().antMatchers("/img/**");
	     web.ignoring().antMatchers("/img/product/**");
	     web.ignoring().antMatchers("/locales/**");
	     web.ignoring().antMatchers("/generateCaptcha/**");
	     
	     //web.ignoring().antMatchers("/");
	}
	
	private void loginFailureHandler(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        AuthenticationException e) throws IOException {
	 log.info("___loginFailureHandler____");
	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        objectMapper.writeValue(response.getWriter(), "Nopity nop!");
	    }

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		ExceptionMappingAuthenticationFailureHandler failureHandler = new ExceptionMappingAuthenticationFailureHandler();
		Map<String, String> failureUrlMap = new HashMap<>();
		failureUrlMap.put(UsernameNotFoundException.class.getName(), "/401?badCredentials");
		failureUrlMap.put(BadCredentialsException.class.getName(), "/401?badCredentials");
		failureUrlMap.put(BadCaptchaException.class.getName(), "/401?badCaptcha");
		failureUrlMap.put(AccountExpiredException.class.getName(), "/401?accountExpired");
		failureUrlMap.put(LockedException.class.getName(), "/401?locked");
		failureUrlMap.put(DisabledException.class.getName(), "/401?dosabled");
		failureHandler.setExceptionMappings(failureUrlMap);
		return failureHandler;
	}
}
