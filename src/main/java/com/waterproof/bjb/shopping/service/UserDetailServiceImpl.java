package com.waterproof.bjb.shopping.service;

import java.util.Arrays;
import java.util.List;

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

import com.waterproof.bjb.shopping.dao.UserDao;
import com.waterproof.bjb.shopping.dao.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "userDetailService")
@Service
public class UserDetailServiceImpl implements UserDetailsService, UserService {

	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	// @PersistenceContext(type = PersistenceContextType.EXTENDED)
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		String strong_salt = "$2a$10$NnlqpBH.dJZLqG//0IWoG.";
		log.info("strong_salt {}, pw: {}", strong_salt, BCrypt.hashpw("123456", strong_salt));
		for (com.waterproof.bjb.shopping.entity.User u : userRepository.findAll()) {
			log.info("{}", u);
		}
		log.info("size {}", userRepository.findAll().size());
		log.info("****loadUserByUsername**** param1:{}", username);
		com.waterproof.bjb.shopping.entity.User user = userDao.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		} else {
			log.info("cName: {}, password: {}", user.getCName(), user.getPassword());
		}
		// 將使用者放入Authentication物件，代表已通過驗證
		Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
				getAuthority());
		// 將Authentication物件放入SecurityContext存放
		SecurityContextHolder.getContext().setAuthentication(auth);
		log.info("auth: {}", SecurityContextHolder.getContext().getAuthentication());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());

	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority(ANONYMOUS), new SimpleGrantedAuthority(USER));
	}

	// public List getUsers() {
	// return userDao.getUserDetails();
	// }

	public List<com.waterproof.bjb.shopping.entity.User> findAll() {
		return userRepository.findAll();
	}

	public com.waterproof.bjb.shopping.entity.User insertByUser(
			com.waterproof.bjb.shopping.entity.User user) {
		log.info("新增使用者：" + user.toString());
		return userRepository.save(user);
	}

	public com.waterproof.bjb.shopping.entity.User update(com.waterproof.bjb.shopping.entity.User user) {
		log.info("更新使用者：" + user.toString());
		return userRepository.save(user);
	}

	public com.waterproof.bjb.shopping.entity.User delete(String id) {
		com.waterproof.bjb.shopping.entity.User user = userRepository.findOne(id);
		userRepository.delete(user);

		log.info("删除使用者：" + user.toString());
		return user;
	}

	public com.waterproof.bjb.shopping.entity.User findById(String id) {
		log.info("獲取使用者 ID ：" + id);
		return userRepository.findOne(id);
	}

}
