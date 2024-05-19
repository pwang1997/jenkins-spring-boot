package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_IN;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.DepotInManager;
import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.mapper.DepotInMapper;
import com.erp.erpspringboot.core.depots.model.DepotInDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@RestController
@RequestMapping(API_V1_DEPOT_IN)
@Validated
public class DepotInControllerImpl implements DepotInController {

  private final DepotManager depotManager;
  private final DepotInMapper depotInMapper;
  private final DepotInManager depotInManager;

  public DepotInControllerImpl(DepotManager depotManager, DepotInMapper depotInMapper,
      DepotInManager depotInManager) {
    this.depotManager = depotManager;
    this.depotInMapper = depotInMapper;
    this.depotInManager = depotInManager;
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<DepotInDTO>> create(@RequestBody @Valid DepotInDTO depotIn) {
    return null;
  }

  @Override
  public ResponseEntity<Responses<DepotInDTO>> list(Integer pageNumber, Integer pageSize) {
    return null;
  }

  @Override
  public ResponseEntity<Response<DepotInDTO>> update(Long id, DepotInDTO depotIn) {
    return null;
  }

  @Override
  public ResponseEntity<Response<DepotInDTO>> delete(Long id) {
    return null;
  }
}
