package com.waterproof.bjb.shopping.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT A FROM Product A where newest = 1 and activate = 1 and promotion_on = 0 ORDER BY usage ASC, inserted DESC")
    public List<Product> getAllNewProduct(); 
	
	@Query(value = "SELECT A FROM Product A where originalPrice - Price != 0 and activate = 1 and promotion_on = 0 ORDER BY usage ASC, updated DESC")
    public List<Product> getDiscountProduct();
	
	@Query(value = "SELECT A FROM Product A where promotion_start <= :nowDate and promotion_end >= :nowDate and promotion_on = 1 and activate = 1 ORDER BY usage ASC, updated DESC")
    public List<Product> getPromotionProduct(@Param("nowDate") Date nowDate);
	
	@Query(value = "SELECT A FROM Product A where activate = 1 ORDER BY usage ASC, inserted DESC")
    public List<Product> getAllActivateProduct();
	
	@Query(value = "SELECT A FROM Product A, Category C where A.categoryId = C.id and A.activate = 1 and (A.name like :name or A.description like :name) ORDER BY A.usage ASC, A.inserted DESC")
    public List<Product> getFilterProduct(@Param("name") String name); 
	
	@Query(value = "SELECT A FROM Product A ORDER BY updated DESC")
	public List<Product> getOrderByUpdatedDesc(); 
	
	
}
