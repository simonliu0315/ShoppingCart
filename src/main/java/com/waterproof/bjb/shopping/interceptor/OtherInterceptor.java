package com.waterproof.bjb.shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.waterproof.bjb.shopping.service.CategoryService;

@Component
public class OtherInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private Environment environment;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("CATEGORY_OBJECTS", categoryService.getAllCategory());
        request.setAttribute("SHOPPING_CURRENCY", environment.getProperty("shopping.currency"));
        request.setAttribute("SHOPPING_CURRENCY_TW", environment.getProperty("shopping.currency.tw"));
        return super.preHandle(request, response, handler);
    }

}
