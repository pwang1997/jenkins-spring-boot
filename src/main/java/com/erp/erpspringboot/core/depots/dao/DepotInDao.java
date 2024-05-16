package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.DepotInBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface DepotInDao extends JpaRepository<DepotInBO, Long> {

}
