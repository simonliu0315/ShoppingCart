package com.waterproof.bjb.shopping.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.OrderStatus;
import com.waterproof.bjb.shopping.repository.OrderStatusRepository;

import lombok.extern.slf4j.Slf4j;


@CacheConfig(cacheNames = {"orderStatus"})
@Slf4j
@Repository
public class EhcacheOrderStatusRepositoryCustom {

	@Autowired
	private OrderStatusRepository orderStatusRepository;
	
	@CachePut(key = "#p0")
    public OrderStatus save(OrderStatus orderStatus) {
		OrderStatus saveOrderStatus = orderStatusRepository.save(orderStatus);
        log.info("新增功能，同步到缓存，直接写入数据库，ID为：" + saveOrderStatus.getId());
        return saveOrderStatus;
    }

    @Cacheable(key = "#p0")
    public OrderStatus selectById(Long id) {
    	log.info("查询功能，缓存未找到，直接读取数据库，ID为：" + id);
        return orderStatusRepository.findOne(id);
    }

    @CachePut(key = "#p0")
    public OrderStatus updateById(OrderStatus user) {
    	log.info("更新功能，更新缓存，直接更新数据库，ID为：" + user.getId());
        return orderStatusRepository.save(user);
    }

    @CacheEvict(key = "#p0")
    public String deleteById(Long id) {
    	log.info("删除功能，删除缓存，直接删除数据库数据，ID为：" + id);
    	orderStatusRepository.delete(id);
        return "删除成功";
    }
    
    @Cacheable(key = "#p0")
    public OrderStatus selectByStatusId(int status) {
    	for(OrderStatus orderStatus: orderStatusRepository.findAll()) {
			if (orderStatus.getStatus() == status) {
				return orderStatus;
			} else {
				return new OrderStatus();
			}
		}
    	return new OrderStatus();
    }
}
