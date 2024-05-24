package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface DepotDao extends JpaRepository<DepotBO, Long> {

  @Modifying
  @Query(
      value = "UPDATE depots SET quantity = quantity + :quantity WHERE id = :id",
      nativeQuery = true
  )
  void updateQuantityById(@Param("id") Long id, @Param("quantity") Long quantity);
}
