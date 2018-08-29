package com.waterproof.bjb.shopping.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.waterproof.bjb.shopping.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, String> {

}
