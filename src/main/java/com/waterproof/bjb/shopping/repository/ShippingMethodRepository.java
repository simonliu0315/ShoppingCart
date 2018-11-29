package com.waterproof.bjb.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.ShippingMethod;

@Repository
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Integer> { 

	@Query(value = "SELECT A FROM ShippingMethod A where status = 1 ORDER BY id")
    public List<ShippingMethod> getActive(); 
}
