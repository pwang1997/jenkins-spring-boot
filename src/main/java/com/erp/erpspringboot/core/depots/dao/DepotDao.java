package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface DepotDao extends JpaRepository<DepotBO, Long> {

}
