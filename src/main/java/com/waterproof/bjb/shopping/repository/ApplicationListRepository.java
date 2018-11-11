package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.ApplicationList;

@Repository
public interface ApplicationListRepository extends JpaRepository<ApplicationList, Integer>{

}
