package com.waterproof.bjb.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.entity.User;
import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.repository.UserRepository;
import com.waterproof.bjb.shopping.repository.UserRoleRepository;
import com.waterproof.bjb.shopping.utils.PasswordUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
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
	
	public List<User> findByVerifyCode(String verifyCode) {
		return userRepository.findByVerifyCode(verifyCode);
	}
	
	public com.waterproof.bjb.shopping.entity.User insertByUser(
			com.waterproof.bjb.shopping.entity.User user, List<UserRole> userRoles) {
		
		user.setPassword(PasswordUtil.getPassword(user.getPassword()));
		log.info("新增使用者：" + user.toString());
		com.waterproof.bjb.shopping.entity.User u = 
		userRepository.save(user);
		for(UserRole userRole : userRoles) {
			log.info("新增使用者role：" + userRole);
			userRoleRepository.saveAndFlush(userRole);
		}
		return u;
	}
}
