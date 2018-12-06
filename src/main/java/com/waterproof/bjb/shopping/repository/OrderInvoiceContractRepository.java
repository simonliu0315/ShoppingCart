package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.OrderInvoiceContract;

@Repository
public interface OrderInvoiceContractRepository extends JpaRepository<OrderInvoiceContract, String> {

}
