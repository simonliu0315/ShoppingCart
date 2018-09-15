package com.waterproof.bjb.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.repository.UserRepository;

@Service
public class SimpleUserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth!= null) {
			if (auth.getPrincipal() instanceof java.lang.String) {
				User user = new User();
				user.setUsername(auth.getPrincipal().toString());
				user.setCName("");
				return user;
	    	} else {
	    		UserDetails currentUser = (UserDetails) auth.getPrincipal();
	    		return userRepository.findOne(currentUser.getUsername());
	    	}
		} else {
			return new User();
		}
	}
}
