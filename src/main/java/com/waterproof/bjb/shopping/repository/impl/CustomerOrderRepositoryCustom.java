package com.waterproof.bjb.shopping.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import com.waterproof.bjb.shopping.controller.ProductController;
import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerOrderRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Page<CustomerOrder> filter(Date startDate, Date endDate, String orderNo, int statusId, int orderby,
			Pageable pageable) {
		CriteriaQuery<CustomerOrder> criteriaQuery = getCriteriaQuery(startDate, endDate, orderNo, statusId, orderby);
		// SQL查詢對象
		TypedQuery<CustomerOrder> createQuery = em.createQuery(criteriaQuery);
		
		//分頁參數
		Integer pageSize = pageable.getPageSize();
		Integer pageNo = pageable.getPageNumber();

		log.info("pageSize {}, pageNo {}", pageSize, pageNo);
		// 计数查询结果条数
		TypedQuery<CustomerOrder> createCountQuery = em.createQuery(criteriaQuery);

		List<CustomerOrder> list = createCountQuery.getResultList();
		log.info("createCountQuery: getCounter :{}", list.size());
		// 实际查询返回分页对象
		int startIndex = pageSize * pageNo;
		createQuery.setFirstResult(startIndex);
		createQuery.setMaxResults(pageable.getPageSize());
		Page<CustomerOrder> pageRst = new PageImpl<CustomerOrder>(createQuery.getResultList(), pageable, list.size());
		return pageRst;

	}

	private CriteriaQuery<CustomerOrder> getCriteriaQuery(Date startDate, Date endDate, String orderNo, int statusId,
			int orderby) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<CustomerOrder> criteriaQuery = criteriaBuilder.createQuery(CustomerOrder.class);
		Root<CustomerOrder> rootFrom = criteriaQuery.from(CustomerOrder.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(orderNo)) {
			log.info("orderNo {}", orderNo);
			Predicate predicate = criteriaBuilder.like(rootFrom.get("orderNo").as(String.class), "%" + orderNo + "%");
			predicates.add(predicate);
		}
		if (statusId != 0) {
			log.info("statusId {}", statusId);
			Predicate predicate = criteriaBuilder.equal(rootFrom.get("statusId").as(Integer.class), statusId);
			predicates.add(predicate);
		}
		if (startDate != null && endDate != null) {
			Predicate predicate = criteriaBuilder.between(rootFrom.get("inserted").as(Date.class), startDate, endDate);
			predicates.add(predicate);
		}
		// if (!StringUtils.isEmpty(q)) {
		// Predicate predicate =
		// criteriaBuilder.like(rootFrom.get("name").as(String.class), "%" + q +
		// "%");
		// predicates.add(predicate);
		// }
		// 格式化参数
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

		// 默认按照id排序（从小到大）
		if (orderby == 1) {
			criteriaQuery.orderBy(criteriaBuilder.desc(rootFrom.get("updated")));
		}
		return criteriaQuery;
	}

	public List<CustomerOrder> getList(Date startDate, Date endDate, String orderNo, int statusId, int orderby) {
		CriteriaQuery<CustomerOrder> criteriaQuery = getCriteriaQuery(startDate, endDate, orderNo, statusId, orderby);
		TypedQuery<CustomerOrder> createQuery = em.createQuery(criteriaQuery);
		return createQuery.getResultList();
	}
}
