package com.waterproof.bjb.shopping.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.service.SimpleUserService;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SimpleUserService userService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	User u = userService.getUser();
    	u.setAddress("");
    	u.setBirthday(null);
    	u.setEmail("");
    	u.setPassword("");
    	boolean isAdmin = false;
    	if (u.getUserRoles() != null) {
    		for(UserRole role : u.getUserRoles()) {
        		if (StringUtils.equals(role.getId().getRole(), "ROLE_ADMIN")) {
        			isAdmin = true;
        		}
        	}	
    	}
    	
        request.setAttribute("USER_OBJECT", u);
        request.setAttribute("isAdmin", isAdmin);
        return super.preHandle(request, response, handler);
    }
}
