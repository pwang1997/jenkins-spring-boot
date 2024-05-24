package com.erp.erpspringboot.core.sales.dao;

import com.erp.erpspringboot.core.sales.model.SaleBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface SaleDao extends JpaRepository<SaleBO, Long> {
  @Modifying
  @Query(
      nativeQuery = true,
      value = "UPDATE sales SET is_deleted = TRUE WHERE id = :id"
  )
  void softDeleteById(@Param("id") Long id);
}
