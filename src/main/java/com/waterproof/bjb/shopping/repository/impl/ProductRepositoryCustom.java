package com.waterproof.bjb.shopping.repository.impl;

import java.util.ArrayList;
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
import com.waterproof.bjb.shopping.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Page<Product> filter(String q, int category, long productId,
    		long price_low, long price_high, int orderby, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> rootFrom = criteriaQuery.from(Product.class);

		//Join<Product, Category> bJoin= rootFrom.join("Category", JoinType.LEFT);
		//bJoin.on(criteriaBuilder.equal(bJoin.get("id"), rootFrom.get("categoryId")));
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(q)) {
			log.info("q {}", q);
		    Predicate predicate = criteriaBuilder.like(rootFrom.get("name").as(String.class), "%" + q + "%");
		    predicates.add(predicate);
		}
		if (category != 0) {
			log.info("category {}", category);
		    Predicate predicate = criteriaBuilder.equal(rootFrom.get("categoryId").as(Integer.class), category);
		    predicates.add(predicate);
		}
//		if (!StringUtils.isEmpty(q)) {
//		    Predicate predicate = criteriaBuilder.like(rootFrom.get("name").as(String.class), "%" + q + "%");
//		    predicates.add(predicate);
//		}
		// 格式化参数
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		// 默认按照id排序（从小到大）
		if (orderby == 1) {
			criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("updated")));
		}
		if (orderby == 2) {
			criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("price")));
		}
		if (orderby == 3) {
			criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("id")));
		}
		// SQL查询对象
		TypedQuery<Product> createQuery = em.createQuery(criteriaQuery);

		// 分页参数
		Integer pageSize = pageable.getPageSize();
		Integer pageNo = pageable.getPageNumber();

		log.info("pageSize {}, pageNo {}", pageSize, pageNo);
		// 计数查询结果条数
		TypedQuery<Product> createCountQuery = em.createQuery(criteriaQuery);

		// 实际查询返回分页对象
		int startIndex = pageSize * pageNo;
		createQuery.setFirstResult(startIndex);
		createQuery.setMaxResults(pageable.getPageSize());
		Page<Product> pageRst = new PageImpl<Product>(createQuery.getResultList(), pageable,
				createCountQuery.getResultList().size());
		return pageRst;

	}

}
