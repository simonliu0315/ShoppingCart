package com.waterproof.bjb.shopping.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderDetail;
import com.waterproof.bjb.shopping.entity.OrderDetailPK;
import com.waterproof.bjb.shopping.entity.UserContract;
import com.waterproof.bjb.shopping.repository.CustomerOrderRepository;
import com.waterproof.bjb.shopping.repository.OrderDetailRepository;
import com.waterproof.bjb.shopping.repository.OrderStatusRepository;
import com.waterproof.bjb.shopping.service.dto.UserContractDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public String saveToOrder(UserContractDto userContractDto, ProductInCartDto productInCartDto, String username) {
		CustomerOrder customerOrder = new CustomerOrder();
		
		UserContract contract = new UserContract();
		
		BeanUtils.copyProperties(userContractDto, contract);
		contract.setUsername(username);
		log.info("UserContract {}", contract);
		customerOrder.setUsername(username);
		customerOrder.setAmount(productInCartDto.getTotalAmt());
		customerOrder.setStatusId(1);
		Date utilDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(utilDate);
		cal.set(Calendar.MILLISECOND, 0);
		customerOrder.setInserted(new Timestamp(utilDate.getTime()));
		customerOrder.setInsert_by(username);
		customerOrder.setUpdated(new Timestamp(utilDate.getTime()));
		customerOrder.setUpdate_by(username);
		
		customerOrder.setId(0);
		customerOrder.setOrderNo(String.valueOf(utilDate.getTime()));
		log.info("CustomerOrder {}", customerOrder);
		customerOrderRepository.save(customerOrder);
		
		
		for(ProductTempOrder order : productInCartDto.getProductsTempOrder()) {
			OrderDetail orderDetail = new OrderDetail();
			OrderDetailPK pk = new OrderDetailPK();
			
			pk.setOrderNo(String.valueOf(utilDate.getTime()));
			pk.setProductId(order.getProductId());
			orderDetail.setId(pk);
			orderDetail.setDiscount(order.getDiscount());
			//orderDetail.setOrderId(String.valueOf(C));
			orderDetail.setPrice(order.getPrice());
			//orderDetail.setProductId(order.getProductId());
			orderDetail.setProductName(order.getProductName());
			orderDetail.setQuantity(order.getQuantity());
			
			log.info("ProductTempOrder {}", orderDetail);
			orderDetailRepository.save(orderDetail);
		}
		return String.valueOf(utilDate.getTime());
	}
	
	public List<CustomerOrder> queryOrder(String username) {
		List<CustomerOrder> list = customerOrderRepository.findByUsername(username);
		for(CustomerOrder c : list) {
			log.info("OrderNo {}", c);
			for(OrderDetail d : c.getOrderDetails()) {
				log.info("OrderDetail {}", d);	
			}
			
		}
		return list;
		
	}
}
