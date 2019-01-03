package com.waterproof.bjb.shopping.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderATM;
import com.waterproof.bjb.shopping.manager.dto.OrderDto;
import com.waterproof.bjb.shopping.repository.CategoryRepository;
import com.waterproof.bjb.shopping.repository.CustomerOrderRepository;
import com.waterproof.bjb.shopping.repository.OrderATMRepository;
import com.waterproof.bjb.shopping.repository.OrderStatusRepository;
import com.waterproof.bjb.shopping.repository.impl.CustomerOrderRepositoryCustom;
import com.waterproof.bjb.shopping.repository.impl.EhcacheOrderStatusRepositoryCustom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	
	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@Autowired
	private EhcacheOrderStatusRepositoryCustom ehcacheOrderStatusRepositoryCustom;
	
	@Autowired
	private CustomerOrderRepositoryCustom customerOrderRepositoryCustom;
	
	@Autowired
	private OrderATMRepository orderATMRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<OrderDto> getAllOrder() {
		List<OrderDto> list = new ArrayList<OrderDto>();
		for(CustomerOrder customerOrder:customerOrderRepository.findAll()) {
			OrderDto dto = new OrderDto();
			BeanUtils.copyProperties(customerOrder, dto);
			log.info("statusId: {}", customerOrder.getStatusId());
			dto.setStatusDescription(getOrderStatusDesc(customerOrder.getStatusId()));
			dto.setId(customerOrder.getId());
			list.add(dto);
		}
		return list;
	}
	
	public String getOrderStatusDesc(int status) {
		return ehcacheOrderStatusRepositoryCustom.selectByStatusId(status).getDescription();
	}
	
	public CustomerOrder getCustomerOrder(String orderNo) {
		log.info("getCustomerOrder {}", orderNo);
		return customerOrderRepository.findByOrderNoOrderByInsertedDesc(orderNo);
	}
	
	public CustomerOrder updateCustomerOrderStatus(Integer id, int status) {
		CustomerOrder order = customerOrderRepository.findOne(id);
		order.setStatusId(status);
		log.info("call customer Order update: {}", order);
		return customerOrderRepository.save(order);
	}
	
	public OrderATM mergeCustomerOrderAtm(String orderNo, String lastFiveAccountNo, String accountName) {
		OrderATM orderATM = new OrderATM();
		orderATM.setOrderNo(orderNo);
		orderATM.setLastFiveAccountNo(lastFiveAccountNo);
		orderATM.setAccountName(accountName);
		return orderATMRepository.saveAndFlush(orderATM);
	}
	public CustomerOrder getCustomerOrder(int id) {
		log.info("getCustomerOrder {}", id);
		return customerOrderRepository.findOne(id);
	}
	
	@Transactional
	public Page<CustomerOrder> getFilterProduct(Date startDate, Date endDate, String orderNo, int statusId, int orderby, Pageable pageable) {
		
		
//		for(CustomerOrder customerOrder : customerOrderRepository.findAll()) {
//			log.info("All customerOrder {}", customerOrder);
//		}
		
		
		
		Page<CustomerOrder> pcustomerOrder = customerOrderRepositoryCustom.filter(startDate, endDate, orderNo, statusId, orderby, pageable);
		List<CustomerOrder> customerOrders = pcustomerOrder.getContent();
		log.info("size: {}" , customerOrders.size());
		for(CustomerOrder customerOrder : customerOrders) {
			
			//product.setName(StringUtils.rightPad(product.getName(), 30, ""));
			log.info("CustomerOrder {}", customerOrder);
			customerOrder.setOrderStatus(ehcacheOrderStatusRepositoryCustom.selectByStatusId(customerOrder.getStatusId()));
		}
		return pcustomerOrder;
	}
	
	public void updateCustomerOrder(int statusId, String orderNo) {
		log.info("getCustomerOrder {}", statusId);
	    customerOrderRepository.setCustomerOrderByOrderNo(statusId, orderNo);
	}
	public List<CustomerOrder> getFilterProduct(Date startDate, Date endDate, String orderNo, int statusId, int orderby) {
		return customerOrderRepositoryCustom.getList(startDate, endDate, orderNo, statusId, orderby);	
	}
	
	public List<Category> getActivateCategory() {
		return categoryRepository.findAll();
	}
		
}
