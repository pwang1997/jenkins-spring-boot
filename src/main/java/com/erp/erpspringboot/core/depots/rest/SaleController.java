package com.erp.erpspringboot.core.depots.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.model.SaleDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface SaleController {

  ResponseEntity<Response<SaleDTO>> create(SaleDTO depotIn);

  ResponseEntity<Responses<SaleDTO>> list(Integer pageNumber, Integer pageSize);

  ResponseEntity<Response<SaleDTO>> update(Long id, SaleDTO depotIn);

  ResponseEntity<Response<SaleDTO>> delete(Long id);
}
