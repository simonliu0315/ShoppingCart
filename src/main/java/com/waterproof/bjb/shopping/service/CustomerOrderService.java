package com.waterproof.bjb.shopping.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.dto.ProductInCartDto;
import com.waterproof.bjb.shopping.dto.ProductTempOrder;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.OrderContract;
import com.waterproof.bjb.shopping.entity.OrderDetail;
import com.waterproof.bjb.shopping.entity.OrderDetailPK;
import com.waterproof.bjb.shopping.entity.OrderInvoiceContract;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.entity.ProductColor;
import com.waterproof.bjb.shopping.entity.ProductColorPK;
import com.waterproof.bjb.shopping.entity.ShippingMethod;
import com.waterproof.bjb.shopping.entity.UserContract;
import com.waterproof.bjb.shopping.repository.CustomerOrderRepository;
import com.waterproof.bjb.shopping.repository.OrderContractRepository;
import com.waterproof.bjb.shopping.repository.OrderDetailRepository;
import com.waterproof.bjb.shopping.repository.OrderInvoiceContractRepository;
import com.waterproof.bjb.shopping.repository.OrderStatusRepository;
import com.waterproof.bjb.shopping.repository.ProductColorRepository;
import com.waterproof.bjb.shopping.repository.ProductRepository;
import com.waterproof.bjb.shopping.repository.ShippingMethodRepository;
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

	@Autowired
	private ProductRepository pruductRepository;
	
	@Autowired
	private ShippingMethodRepository shippingMethodRepository;
	
	@Autowired
	private OrderContractRepository orderContractRepository;
	
	@Autowired
	private ProductColorRepository productColorRepository;
	
	@Autowired
	private OrderInvoiceContractRepository orderInvoiceContractRepository;
	

	public String saveToOrder(UserContractDto userContractDto, ProductInCartDto productInCartDto, String username) {
		CustomerOrder customerOrder = new CustomerOrder();

		UserContract contract = new UserContract();
		OrderContract orderContract = new OrderContract();
		OrderInvoiceContract orderInvoiceContract = new OrderInvoiceContract();
		BeanUtils.copyProperties(userContractDto, contract);
		BeanUtils.copyProperties(userContractDto, orderContract);
		BeanUtils.copyProperties(userContractDto, orderInvoiceContract);
		contract.setUsername(username);
		log.info("UserContract {}", contract);
		log.info("orderContract {}", orderContract);
		log.info("orderInvoiceContract {}", orderInvoiceContract);
		customerOrder.setUsername(username);
		customerOrder.setAmount(productInCartDto.getTotalAmt());
		customerOrder.setStatusId(1);
		customerOrder.setMethod(productInCartDto.getPaymentMethod());
		customerOrder.setShipping(productInCartDto.getShipment());
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

		for (ProductTempOrder order : productInCartDto.getProductsTempOrder()) {
			OrderDetail orderDetail = new OrderDetail();
			OrderDetailPK pk = new OrderDetailPK();

			pk.setOrderNo(String.valueOf(utilDate.getTime()));
			pk.setProductId(order.getProductId());
			pk.setColor(StringUtils.defaultString(order.getColor()));
			orderDetail.setId(pk);
			orderDetail.setDiscount(order.getDiscount());
			// orderDetail.setOrderId(String.valueOf(C));
			orderDetail.setPrice(order.getPrice());
			// orderDetail.setProductId(order.getProductId());
			orderDetail.setProductName(order.getProductName());
			orderDetail.setQuantity(order.getQuantity());
			
			log.info("ProductTempOrder {}", orderDetail);
			orderDetailRepository.save(orderDetail);
		}
		orderContract.setOrderNo(customerOrder.getOrderNo());
		orderInvoiceContract.setInvoiceType(userContractDto.getInvoiceType());
		orderInvoiceContract.setOrderNo(customerOrder.getOrderNo());
		orderContractRepository.save(orderContract);
		log.info("orderInvoiceContract {}", orderInvoiceContract);
		orderInvoiceContractRepository.save(orderInvoiceContract);
		return String.valueOf(utilDate.getTime());
	}

	/**
	 * @param username
	 * @return
	 */
	public List<ProductInCartDto> queryOrder(String username) {
		List<ProductInCartDto> retList = new ArrayList<ProductInCartDto>();

		List<CustomerOrder> list = customerOrderRepository.findByUsernameOrderByInsertedDesc(username);
		for (CustomerOrder c : list) {
			ProductInCartDto dto = new ProductInCartDto();
			dto.setUserId(username);
			log.info("OrderNo {}", c);
			for (OrderDetail d : c.getOrderDetails()) {
				ProductTempOrder tmp = new ProductTempOrder();
				log.info("OrderDetail {}", d);
				BeanUtils.copyProperties(d, tmp);
				Product p = pruductRepository.findOne(Long.parseLong(d.getId().getProductId().toString()));
				tmp.setImg(p.getImg());
				tmp.setDiscount(p.getDiscount());
				tmp.setDescription(p.getDescription());
				tmp.setOriginalPrice(p.getOriginalPrice());
				tmp.setProductId(p.getId().intValue());
				tmp.setColor(d.getId().getColor());
				tmp.setColorName(getColorName(d.getId().getProductId(), d.getId().getColor()));
				
				dto.getProductsTempOrder().add(tmp);
			}
			BeanUtils.copyProperties(c, dto);
			log.info("paymentMethod: {}", c.getPaymentMethod());
			
			dto.setTotalAmt(c.getAmount());
			dto.setPaymentMethod(c.getPaymentMethod().getMethod());
			dto.setOrderStatus(c.getOrderStatus().getDescription());
			dto.setPaymentMethodStr(c.getPaymentMethod().getDescription());
			log.info("dto: {}", dto);
			retList.add(dto);
		}

		return retList;

	}

	public ProductInCartDto queryOrderByNo(String orderNo) {

		CustomerOrder customerOrder = customerOrderRepository.findByOrderNoOrderByInsertedDesc(orderNo);

		ProductInCartDto dto = new ProductInCartDto();
		// dto.setUserId(username);
		log.info("OrderNo {}", customerOrder);
		for (OrderDetail d : customerOrder.getOrderDetails()) {
			ProductTempOrder tmp = new ProductTempOrder();
			log.info("OrderDetail {}", d);
			BeanUtils.copyProperties(d, tmp);
			Product p = pruductRepository.findOne(Long.parseLong(d.getId().getProductId().toString()));
			tmp.setImg(p.getImg());
			tmp.setDiscount(p.getDiscount());
			tmp.setDescription(p.getDescription());
			tmp.setOriginalPrice(p.getOriginalPrice());
			tmp.setColor(d.getId().getColor());
			tmp.setColorName(getColorName(d.getId().getProductId(), d.getId().getColor()));
			dto.getProductsTempOrder().add(tmp);
		}
		BeanUtils.copyProperties(customerOrder, dto);
		log.info("paymentMethod: {}", customerOrder.getPaymentMethod());
		dto.setPaymentMethod(customerOrder.getPaymentMethod().getMethod());
		dto.setOrderStatus(customerOrder.getOrderStatus().getDescription());
		dto.setPaymentMethodStr(customerOrder.getPaymentMethod().getDescription());
		log.info("dto: {}", dto);

		return dto;

	}
	
	public List<ShippingMethod> getShipping() {
		return shippingMethodRepository.getActive();
	}
	
	public String getColorName(int productId, String color) {
		ProductColorPK id = new ProductColorPK();
		id.setProductId(productId);
		id.setColor(color);
		ProductColor productColor = productColorRepository.findOne(id);
		if (productColor != null) {
			return productColor.getName();
		} else {
			return null;
		}
		
	}
}
