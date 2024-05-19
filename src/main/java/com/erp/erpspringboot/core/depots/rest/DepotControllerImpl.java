package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOTS;

import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.mapper.DepotMapper;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.DepotDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@RestController
@RequestMapping(API_V1_DEPOTS)
public class DepotControllerImpl implements DepotController {

  private final DepotManager depotManager;
  private final DepotMapper depotMapper;

  public DepotControllerImpl(DepotManager depotManager, DepotMapper depotMapper) {
    this.depotManager = depotManager;
    this.depotMapper = depotMapper;
  }

  @Override
  @GetMapping("/list")
  public ResponseEntity<Responses<DepotDTO>> list(
      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
  ) {
    List<DepotBO> depotBOS = depotManager.list(pageNumber, pageSize);
    List<DepotDTO> depotDTOS = depotBOS.stream().map(depotMapper::mapToDTO).toList();
    return ResponseEntity.ok(new Responses<>(depotDTOS));
  }
}
