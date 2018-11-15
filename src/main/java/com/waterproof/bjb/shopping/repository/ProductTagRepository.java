package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.ProductTag;

@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {

}
