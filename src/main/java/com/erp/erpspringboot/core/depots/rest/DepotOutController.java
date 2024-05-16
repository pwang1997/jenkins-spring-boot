package com.erp.erpspringboot.core.depots.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.model.DepotOutDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface DepotOutController {

  ResponseEntity<Response<DepotOutDTO>> create(DepotOutDTO depotIn);

  ResponseEntity<Responses<DepotOutDTO>> list(Integer pageNumber, Integer pageSize);

  ResponseEntity<Response<DepotOutDTO>> update(Long id, DepotOutDTO depotIn);

  ResponseEntity<Response<DepotOutDTO>> delete(Long id);
}
