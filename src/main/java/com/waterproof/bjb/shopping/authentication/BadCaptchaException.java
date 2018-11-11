package com.waterproof.bjb.shopping.authentication;

import org.springframework.security.core.AuthenticationException;

public class BadCaptchaException extends AuthenticationException {
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message.
	 *
	 * @param msg the detail message
	 */
	public BadCaptchaException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>BadCredentialsException</code> with the specified message and
	 * root cause.
	 *
	 * @param msg the detail message
	 * @param t root cause
	 */
	public BadCaptchaException(String msg, Throwable t) {
		super(msg, t);
	}

}
