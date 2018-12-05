package com.waterproof.bjb.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	
	public List<CustomerOrder> findByUsernameOrderByInsertedDesc(String username);
	
	public CustomerOrder findByOrderNoOrderByInsertedDesc(String orderNo);
	
	public CustomerOrder findByIdOrderByInsertedDesc(int id);
	
	@Modifying
	@Query("update CUSTOMER_ORDER co set co.status_id = ?1 where co.order_no = ?2")
	public void setCustomerOrderByOrderNo(int statusId, String orderNo);
}
