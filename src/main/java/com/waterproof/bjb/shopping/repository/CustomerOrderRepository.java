package com.waterproof.bjb.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	
	public List<CustomerOrder> findByUsernameOrderByInsertedDesc(String username);
	
	public CustomerOrder findByOrderNoOrderByInsertedDesc(String orderNo);
	
	public CustomerOrder findByIdOrderByInsertedDesc(int id);
}
