package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.entity.AuditLogPK;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, AuditLogPK>{

}
