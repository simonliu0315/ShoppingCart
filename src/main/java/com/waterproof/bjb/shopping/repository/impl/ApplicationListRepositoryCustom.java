package com.waterproof.bjb.shopping.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import com.waterproof.bjb.shopping.entity.ApplicationList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ApplicationListRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	public Page<ApplicationList> filter(String q,
			int orderby, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<ApplicationList> criteriaQuery = criteriaBuilder.createQuery(ApplicationList.class);
		Root<ApplicationList> rootFrom = criteriaQuery.from(ApplicationList.class);
	
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate predicate = criteriaBuilder.equal(rootFrom.get("status").as(Integer.class), 1);
	    predicates.add(predicate);
		if (!StringUtils.isEmpty(q)) {
			log.info("q {}", q);
		    predicate = criteriaBuilder.like(rootFrom.get("name").as(String.class), "%" + q + "%");
		    predicates.add(predicate);
		}
		// 格式化参数
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		// 默认按照id排序（从小到大）
		if (orderby == 1) {
			criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("id")));
		}
		if (orderby == 2) {
			criteriaQuery.orderBy(criteriaBuilder.asc(rootFrom.get("updated")));
		}
		// SQL查询对象
		TypedQuery<ApplicationList> createQuery = em.createQuery(criteriaQuery);

		// 分页参数
		Integer pageSize = pageable.getPageSize();
		Integer pageNo = pageable.getPageNumber();

		log.info("pageSize {}, pageNo {}", pageSize, pageNo);
		// 计数查询结果条数
		TypedQuery<ApplicationList> createCountQuery = em.createQuery(criteriaQuery);

		// 实际查询返回分页对象
		int startIndex = pageSize * pageNo;
		createQuery.setFirstResult(startIndex);
		createQuery.setMaxResults(pageable.getPageSize());
		Page<ApplicationList> pageRst = new PageImpl<ApplicationList>(createQuery.getResultList(), pageable,
				createCountQuery.getResultList().size());
		return pageRst;

	}

}
