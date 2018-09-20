package com.waterproof.bjb.shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.waterproof.bjb.shopping.service.CategoryService;

@Component
public class OtherInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CategoryService categoryService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("CATEGORY_OBJECTS", categoryService.getAllCategory());
        return super.preHandle(request, response, handler);
    }

}
