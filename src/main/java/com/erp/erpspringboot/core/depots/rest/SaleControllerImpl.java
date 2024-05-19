package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_OUT;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.SaleManager;
import com.erp.erpspringboot.core.depots.mapper.SaleMapper;
import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import com.erp.erpspringboot.core.depots.model.InvoiceDTO;
import com.erp.erpspringboot.core.depots.model.SaleBO;
import com.erp.erpspringboot.core.depots.model.SaleDTO;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@RestController
@RequestMapping(API_V1_DEPOT_OUT)
@Validated
public class SaleControllerImpl implements SaleController {

  private final SaleMapper saleMapper;
  private final SaleManager saleManager;

  public SaleControllerImpl(SaleMapper saleMapper, SaleManager saleManager) {
    this.saleMapper = saleMapper;
    this.saleManager = saleManager;
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<SaleDTO>> create(@RequestBody @Valid SaleDTO saleDTO) {
    SaleBO saleBO = saleMapper.mapToBO(saleDTO);

    saleBO = saleManager.create(saleBO);

    SaleDTO updatedSaleDTO = saleMapper.mapToDTO(saleBO);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(updatedSaleDTO.getId())
        .toUri();
    return ResponseEntity.created(location).body(new Response<>(updatedSaleDTO));
  }

  @Override
  @GetMapping("/list")
  public ResponseEntity<Responses<SaleDTO>> list(
      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
  ) {
    List<SaleBO> saleBOS = saleManager.list(pageNumber, pageSize);
    List<SaleDTO> saleDTOS = saleBOS.stream().map(saleMapper::mapToDTO).toList();
    return ResponseEntity.ok(new Responses<>(saleDTOS));
  }

  @Override
  public ResponseEntity<Response<SaleDTO>> update(
      @PathVariable(name = "id") Long id,
      @RequestBody SaleDTO depotIn) {
    SaleBO saleBO = saleMapper.mapToBO(depotIn);
    SaleBO updated = saleManager.update(id, saleBO);

    SaleDTO updatedSaleDTO = saleMapper.mapToDTO(updated);
    return ResponseEntity.ok(new Response<>(updatedSaleDTO));
  }

  @Override
  public ResponseEntity<Response<SaleDTO>> delete(Long id) {
    return null;
  }
}
