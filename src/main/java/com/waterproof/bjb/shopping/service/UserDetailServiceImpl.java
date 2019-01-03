package com.waterproof.bjb.shopping.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "userDetailService")
@Service
public class UserDetailServiceImpl implements UserDetailsService {

	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public UserDetails loadUserByUsername(String username) {
		
		log.info("size {}", userRepository.findAll().size());
		log.info("****loadUserByUsername**** param username:{}", username);
		com.waterproof.bjb.shopping.entity.User user = userRepository.findOne(username);
		log.info("user :{}", user);
		if (user == null || user.getStatus() != 1) {
			
			throw new UsernameNotFoundException("Invalid username or password.");
		} else {
			log.info("cName: {}, password: {}", user.getCName(), user.getPassword());
		}
		log.info("user ==>{}", user);
		// 將使用者放入Authentication物件，代表已通過驗證
		Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				getAuthority(user));
		// 將Authentication物件放入SecurityContext存放
		SecurityContextHolder.getContext().setAuthentication(auth);
		log.info("auth: {}", SecurityContextHolder.getContext().getAuthentication());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));

	}

	private List<SimpleGrantedAuthority> getAuthority(com.waterproof.bjb.shopping.entity.User user) {
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		for(UserRole role: user.getUserRoles()) {
			log.info("{}", role);
			if (role.getStatus() == 1) {
				list.add(new SimpleGrantedAuthority(role.getId().getRole()));
			}
		}
		return list;
		//return Arrays.asList(new SimpleGrantedAuthority(ANONYMOUS), new SimpleGrantedAuthority(USER), new SimpleGrantedAuthority(ADMIN));
	}

	// public List getUsers() {
	// return userDao.getUserDetails();
	// }

	

}
