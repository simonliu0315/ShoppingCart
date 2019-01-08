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
import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.entity.CustomerOrder;
import com.waterproof.bjb.shopping.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AuditLogRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Page<AuditLog> filter(Date startDate, Date endDate, String userName, int status, int orderby,
			Pageable pageable) {
		CriteriaQuery<AuditLog> criteriaQuery = getCriteriaQuery(startDate, endDate, userName, status, orderby);
		// SQL查詢對象
		TypedQuery<AuditLog> createQuery = em.createQuery(criteriaQuery);
		
		//分頁參數
		Integer pageSize = pageable.getPageSize();
		Integer pageNo = pageable.getPageNumber();

		log.info("pageSize {}, pageNo {}", pageSize, pageNo);
		// 计数查询结果条数
		TypedQuery<AuditLog> createCountQuery = em.createQuery(criteriaQuery);

		List<AuditLog> list = createCountQuery.getResultList();
		log.info("createCountQuery: getCounter :{}", list.size());
		// 实际查询返回分页对象
		int startIndex = pageSize * pageNo;
		createQuery.setFirstResult(startIndex);
		createQuery.setMaxResults(pageable.getPageSize());
		Page<AuditLog> pageRst = new PageImpl<AuditLog>(createQuery.getResultList(), pageable, list.size());
		return pageRst;

	}

	private CriteriaQuery<AuditLog> getCriteriaQuery(Date startDate, Date endDate, String userName, int status, int orderby) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<AuditLog> criteriaQuery = criteriaBuilder.createQuery(AuditLog.class);
		Root<AuditLog> rootFrom = criteriaQuery.from(AuditLog.class);
log.info("getModel: {}", rootFrom.getModel());
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(userName)) {
			log.info("userName {}", userName);
			Predicate predicate = criteriaBuilder.like(rootFrom.get("insert_by").as(String.class), "%" + userName + "%");
			predicates.add(predicate);
		}
		if (status != 0) {
			log.info("status {}", status);
			Predicate predicate = criteriaBuilder.equal(rootFrom.get("status").as(Integer.class), status);
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

		//log.info("getName {}", rootFrom.get);
		// 默认按照id排序（从小到大）
		if (orderby == 1) {
			//criteriaQuery.orderBy(criteriaBuilder.desc(rootFrom.get(new AuditLog().getId().getClass().getName())));
		}
		return criteriaQuery;
	}

	public List<AuditLog> getList(Date startDate, Date endDate, String userName, int status, int orderby) {
		CriteriaQuery<AuditLog> criteriaQuery = getCriteriaQuery(startDate, endDate, userName, status, orderby);
		TypedQuery<AuditLog> createQuery = em.createQuery(criteriaQuery);
		return createQuery.getResultList();
	}
}
