package com.waterproof.bjb.shopping.commons;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.entity.AuditLogPK;
import com.waterproof.bjb.shopping.service.AuditService;
import com.waterproof.bjb.shopping.service.SimpleUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggableDispatcherServlet extends DispatcherServlet {

	@Autowired
	private AuditService auditService;
	
	@Autowired
	private SimpleUserService userService;
	
	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!(request instanceof ContentCachingRequestWrapper)) {
			request = new ContentCachingRequestWrapper(request);
		}
		if (!(response instanceof ContentCachingResponseWrapper)) {
			response = new ContentCachingResponseWrapper(response);
		}
		HandlerExecutionChain handler = getHandler(request);

		try {
			super.doDispatch(request, response);
		} finally {
			auditlog(request, response, handler);
			updateResponse(response);
		}
	}

	private void auditlog(HttpServletRequest requestToCache, HttpServletResponse responseToCache,
			HandlerExecutionChain handler) {
		if (requestToCache.getRequestURI() != null && (requestToCache.getRequestURI().indexOf("/css") == -1
				&& requestToCache.getRequestURI().indexOf("/js") == -1
				&& requestToCache.getRequestURI().indexOf("/img") == -1
				&& requestToCache.getRequestURI().indexOf("/image") == -1
				&& requestToCache.getRequestURI().indexOf("/fonts") == -1)) {
			Date utilDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(utilDate);
			cal.set(Calendar.MILLISECOND, 0);
			AuditLog auditlog = new AuditLog();
			AuditLogPK id = new AuditLogPK();
			id.setSessionId(requestToCache.getRequestedSessionId());
			id.setFunctionId(requestToCache.getRequestURI());
			id.setInserted(new Timestamp(utilDate.getTime()));
			auditlog.setId(id);
			auditlog.setHttpStatus(responseToCache.getStatus());
			auditlog.setHttpMethod(requestToCache.getMethod());
			auditlog.setUri(requestToCache.getRequestURI());
			auditlog.setIp(requestToCache.getRemoteAddr());
			if (handler == null) {
				auditlog.setJavaMethod("");
			} else {
				auditlog.setJavaMethod(handler.toString());
			}
			String response = getResponsePayload(responseToCache).replaceAll("\u0000", "");
			
			auditlog.setResponse(response);
			auditlog.setInsert_by(userService.getUser().getUsername());
			log.info("auditlog {}", auditlog);
			auditService.createAudit(auditlog);
		}
	}

	private String getResponsePayload(HttpServletResponse response) {
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		if (wrapper != null) {

			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, 64);
				try {
					return new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					// NOOP
				}
			}
		}
		return "[unknown]";
	}

	private void updateResponse(HttpServletResponse response) throws IOException {
		ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		responseWrapper.copyBodyToResponse();
	}

}
