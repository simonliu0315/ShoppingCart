package com.waterproof.bjb.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
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
