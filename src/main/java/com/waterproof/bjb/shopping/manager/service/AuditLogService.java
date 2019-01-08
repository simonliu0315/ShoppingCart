package com.waterproof.bjb.shopping.manager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.repository.impl.AuditLogRepositoryCustom;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditLogService {

	
	@Autowired
	private AuditLogRepositoryCustom auditLogRepositoryCustom;

	
	@Transactional
	public Page<AuditLog> getFilterAuditLog(Date startDate, Date endDate, String userName, int status, int orderby, Pageable pageable) {
		
		Page<AuditLog> pauditlog = auditLogRepositoryCustom.filter(startDate, endDate, userName, status, orderby, pageable);
		List<AuditLog> auditlogs = pauditlog.getContent();
		log.info("size: {}" , auditlogs.size());
		
		return pauditlog;
	}
	
	
		
}
