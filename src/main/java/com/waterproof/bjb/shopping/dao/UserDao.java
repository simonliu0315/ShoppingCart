package com.waterproof.bjb.shopping.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDao {

	@Autowired
	private UserRepository userRepository;
	
	public User findUserByUsername(String userName) {
		log.info("****findUserById****,{}1" , userName);
		log.info("****findUserById****,{}" , userRepository.getOne(userName)==null);
		
		return userRepository.getOne(userName);
		
	}
}
