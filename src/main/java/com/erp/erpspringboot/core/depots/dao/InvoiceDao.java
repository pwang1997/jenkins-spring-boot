package com.erp.erpspringboot.core.depots.dao;

import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface InvoiceDao extends JpaRepository<InvoiceBO, Long> {

}