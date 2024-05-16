package com.erp.erpspringboot.core.depots.rest;

import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.model.DepotDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface DepotController {

  ResponseEntity<Responses<DepotDTO>> list(Integer pageNumber, Integer pageSize);
}
