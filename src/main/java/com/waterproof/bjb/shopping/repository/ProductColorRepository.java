package com.waterproof.bjb.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.ProductColor;
import com.waterproof.bjb.shopping.entity.ProductColorPK;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, ProductColorPK> {

	@Query(value = "SELECT A FROM ProductColor A where A.id.productId = :productId order by A.seq, A.id.color")
    public List<ProductColor> getProductColor(@Param("productId") int productId);
}
