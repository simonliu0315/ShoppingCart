package com.waterproof.bjb.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

	public List<CustomerOrder> findByUsernameOrderByInsertedDesc(String username);

	public CustomerOrder findByOrderNoOrderByInsertedDesc(String orderNo);

	public CustomerOrder findByIdOrderByInsertedDesc(int id);

	@Modifying
	@Transactional
	@Query("update CustomerOrder co set co.statusId =:statusId  where co.orderNo = :orderNo")
	public void setCustomerOrderByOrderNo(@Param("statusId") int statusId, @Param("orderNo") String orderNo);
}
