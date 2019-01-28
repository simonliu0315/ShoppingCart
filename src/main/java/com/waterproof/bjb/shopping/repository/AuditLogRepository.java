package com.waterproof.bjb.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.waterproof.bjb.shopping.entity.AuditLog;
import com.waterproof.bjb.shopping.entity.AuditLogPK;

import java.sql.Timestamp;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, AuditLogPK> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM AuditLog A where A.id.inserted < :inserted" )
    public void deleteByDate(@Param("inserted") Timestamp inserted);
}
