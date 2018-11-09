package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.UserRole;
import com.waterproof.bjb.shopping.entity.UserRolePK;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> { 

}
