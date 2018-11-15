package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.ProductTagRelation;
import com.waterproof.bjb.shopping.entity.ProductTagRelationPK;

@Repository
public interface ProductTagRelationRepository extends JpaRepository<ProductTagRelation, ProductTagRelationPK> { 

}
