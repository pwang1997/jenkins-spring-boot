package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_IN;
import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_OUT;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.DepotInManager;
import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.mapper.DepotInMapper;
import com.erp.erpspringboot.core.depots.model.DepotInDTO;
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
  private final DepotInMapper depotInMapper;
  private final DepotInManager depotInManager;

  public DepotOutControllerImpl(DepotManager depotManager, DepotInMapper depotInMapper,
      DepotInManager depotInManager) {
    this.depotManager = depotManager;
    this.depotInMapper = depotInMapper;
    this.depotInManager = depotInManager;
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
