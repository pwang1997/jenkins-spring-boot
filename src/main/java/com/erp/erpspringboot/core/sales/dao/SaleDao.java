package com.erp.erpspringboot.core.sales.dao;

import com.erp.erpspringboot.core.sales.model.SaleBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface SaleDao extends JpaRepository<SaleBO, Long> {

}
