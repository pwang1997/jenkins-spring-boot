package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_OUT;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.InvoiceManager;
import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.mapper.InvoiceMapper;
import com.erp.erpspringboot.core.depots.model.DepotOutDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@RestController
@RequestMapping(API_V1_DEPOT_OUT)
public class DepotOutControllerImpl implements DepotOutController {

  private final DepotManager depotManager;
  private final InvoiceMapper invoiceMapper;
  private final InvoiceManager invoiceManager;

  public DepotOutControllerImpl(DepotManager depotManager, InvoiceMapper invoiceMapper,
      InvoiceManager invoiceManager) {
    this.depotManager = depotManager;
    this.invoiceMapper = invoiceMapper;
    this.invoiceManager = invoiceManager;
  }

  @Override
  public ResponseEntity<Response<DepotOutDTO>> create(DepotOutDTO depotIn) {
    return null;
  }

  @Override
  public ResponseEntity<Responses<DepotOutDTO>> list(Integer pageNumber, Integer pageSize) {
    return null;
  }

  @Override
  public ResponseEntity<Response<DepotOutDTO>> update(Long id, DepotOutDTO depotIn) {
    return null;
  }

  @Override
  public ResponseEntity<Response<DepotOutDTO>> delete(Long id) {
    return null;
  }
}
