package com.erp.erpspringboot.core.vendors.dao;

import com.erp.erpspringboot.core.vendors.model.VendorBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

public interface VendorDao extends JpaRepository<VendorBO, Long>,
    PagingAndSortingRepository<VendorBO, Long> {

}
