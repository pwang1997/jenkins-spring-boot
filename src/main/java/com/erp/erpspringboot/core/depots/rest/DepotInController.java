package com.erp.erpspringboot.core.depots.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.model.DepotInDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface DepotInController {

  ResponseEntity<Response<DepotInDTO>> create(DepotInDTO depotIn);

  ResponseEntity<Responses<DepotInDTO>> list(Integer pageNumber, Integer pageSize);

  ResponseEntity<Response<DepotInDTO>> update(Long id, DepotInDTO depotIn);

  ResponseEntity<Response<DepotInDTO>> delete(Long id);
}
