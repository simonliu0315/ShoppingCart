package com.waterproof.bjb.shopping.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderStatus;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;
import com.waterproof.bjb.shopping.repository.CustomerOrderRepository;
import com.waterproof.bjb.shopping.repository.OrderStatusRepository;
import com.waterproof.bjb.shopping.repository.impl.EhcacheOrderStatusRepositoryCustom;

@Service
public class OrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	private EhcacheOrderStatusRepositoryCustom ehcacheOrderStatusRepositoryCustom;
	
	public List<OrderDto> getAllOrder() {
		List<OrderDto> list = new ArrayList<OrderDto>();
		for(CustomerOrder customerOrder:customerOrderRepository.findAll()) {
			OrderDto dto = new OrderDto();
			BeanUtils.copyProperties(customerOrder, dto);
			dto.setStatusDescription(getOrderStatusDesc(customerOrder.getStatusId()));
			list.add(dto);
		}
		return list;
	}
	
	public String getOrderStatusDesc(int status) {
		return ehcacheOrderStatusRepositoryCustom.selectByStatusId(status).getDescription();
	}
}
