package com.erp.erpspringboot.core.users.dao;

import com.erp.erpspringboot.core.users.model.PermissionBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

public interface PermissionDao extends JpaRepository<PermissionBO, String> {

}
