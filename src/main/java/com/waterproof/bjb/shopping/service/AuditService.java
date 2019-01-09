package com.waterproof.bjb.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.repository.AuditLogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuditService {

	@Autowired
	private AuditLogRepository auditLogRepository;

	public AuditLog createAudit(AuditLog auditLog) {
		log.info("call createAudit. {}", auditLog);
		AuditLog auditLogRet = null;
		try {
			if (auditLog.getId().getSessionId() == null) {
				return auditLog;
			}
			auditLogRet = auditLogRepository.saveAndFlush(auditLog);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return auditLogRet;
	}
}
