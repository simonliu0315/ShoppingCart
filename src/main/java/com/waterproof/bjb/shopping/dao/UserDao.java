package com.waterproof.bjb.shopping.dao;

import org.springframework.stereotype.Component;

import com.waterproof.bjb.shopping.entity.UserDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDao {

	public UserDetails findUserById(String id) {
		log.info("****findUserById****");
		return new UserDetails();
	}
}
