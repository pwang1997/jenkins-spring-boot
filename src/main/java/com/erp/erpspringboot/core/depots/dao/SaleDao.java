package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.SaleBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface SaleDao extends JpaRepository<SaleBO, Long> {

}