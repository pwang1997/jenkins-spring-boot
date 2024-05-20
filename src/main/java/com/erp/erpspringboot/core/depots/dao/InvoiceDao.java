package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface InvoiceDao extends JpaRepository<InvoiceBO, Long> {

  @Modifying
  @Query(
      nativeQuery = true,
      value = "UPDATE invoices SET is_deleted = TRUE WHERE id = :id"
  )
  void softDeleteById(@Param("id") Long id);
}
