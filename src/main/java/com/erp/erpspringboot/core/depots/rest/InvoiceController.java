package com.erp.erpspringboot.core.depots.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.model.InvoiceDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
public interface InvoiceController {

  ResponseEntity<Response<InvoiceDTO>> create(InvoiceDTO depotIn);

  ResponseEntity<Responses<InvoiceDTO>> list(Integer pageNumber, Integer pageSize);

  ResponseEntity<Response<InvoiceDTO>> update(Long id, InvoiceDTO depotIn);

  ResponseEntity<Response<InvoiceDTO>> delete(Long id);
}
