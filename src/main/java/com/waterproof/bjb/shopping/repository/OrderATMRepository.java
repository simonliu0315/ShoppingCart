package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.OrderATM;
import com.waterproof.bjb.shopping.entity.OrderStatus;

@Repository
public interface OrderATMRepository extends JpaRepository<OrderATM, String> {

}
