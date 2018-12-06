package com.waterproof.bjb.shopping.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.expression.Lists;
import org.thymeleaf.util.StringUtils;

import com.waterproof.bjb.shopping.controller.ProductController;
import com.waterproof.bjb.shopping.entity.Category;
import com.waterproof.bjb.shopping.entity.Product;
import com.waterproof.bjb.shopping.entity.ProductTag;
import com.waterproof.bjb.shopping.entity.ProductTagRelation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ProductRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Page<Product> filter(String q, int category, long productId,
    		long price_low, long price_high, int orderby, Pageable pageable, int[] tagId) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class).distinct(true);;
		Root<Product> rootFrom = criteriaQuery.from(Product.class);

		
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (tagId != null) {
			Join<Product, ProductTagRelation> productTagRelation = rootFrom.join("productTagRelations", JoinType.INNER);
			
			Join<ProductTagRelation, ProductTag> productTag = productTagRelation.join("productTag", JoinType.INNER);
			
			List<Integer> a = new ArrayList<Integer>();
			for (int t : tagId) {
				log.info("ttttttt:{}", t);
				a.add(t);
			}
		    Predicate predicate = productTag.get("id").in(a);
		    predicates.add(predicate);
		}
		
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
		if (price_low != 0) {
			log.info("price_low {}", price_low);
		    Predicate predicate = criteriaBuilder.ge(rootFrom.get("price").as(Integer.class), price_low);
		    predicates.add(predicate);
		}
		if (price_high != 0) {
			log.info("price_high {}", price_high);
		    Predicate predicate = criteriaBuilder.le(rootFrom.get("price").as(Integer.class), price_high);
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
