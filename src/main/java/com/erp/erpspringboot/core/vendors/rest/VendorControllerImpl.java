package com.erp.erpspringboot.core.vendors.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_VENDORS;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.vendors.VendorManager;
import com.erp.erpspringboot.core.vendors.mapper.VendorMapper;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import com.erp.erpspringboot.core.vendors.model.VendorDTO;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

@RestController
@RequestMapping(API_V1_VENDORS)
@Slf4j
public class VendorControllerImpl implements VendorController {

  private final VendorManager vendorManager;
  private final VendorMapper vendorMapper;

  public VendorControllerImpl(VendorManager vendorManager, VendorMapper vendorMapper) {
    this.vendorManager = vendorManager;
    this.vendorMapper = vendorMapper;
  }

  @Override
  @GetMapping("/list")
  public ResponseEntity<Responses<VendorDTO>> list(
      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
  ) {
    log.debug("List vendors: [pageSize: {}, pageNumber: {}]", pageSize, pageNumber);
    List<VendorBO> vendorBOs = vendorManager.list(pageNumber, pageSize);
    List<VendorDTO> vendorDTOs = vendorBOs.stream().map(vendorMapper::mapToDTO).toList();
    return ResponseEntity.ok(new Responses<>(vendorDTOs));
  }

  @Override
  @PostMapping("/create")
  public ResponseEntity<Response<VendorDTO>> create(@Valid @RequestBody VendorDTO vendorDTO) {
    log.debug("Creating vendor: {}", vendorDTO);
    VendorBO createdVendorBO = vendorManager.create(vendorMapper.mapToBO(vendorDTO));
    VendorDTO createdVendorDTO = vendorMapper.mapToDTO(createdVendorBO);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdVendorDTO.getId())
        .toUri();
    log.debug("Created vendor: {}", createdVendorDTO);
    return ResponseEntity.created(location).body(new Response<>(createdVendorDTO));
  }

  @Override
  @PutMapping("/update/{id}")
  public ResponseEntity<Response<VendorDTO>> update(@PathVariable(name = "id") Long id,
      @RequestBody VendorDTO vendorDTO) {
    log.debug("Updating vendor: {}", vendorDTO);
    VendorBO updatedVendorBO = vendorManager.update(id, vendorMapper.mapToBO(vendorDTO));
    VendorDTO updatedVendorDTO = vendorMapper.mapToDTO(updatedVendorBO);
    log.debug("Updated vendor: {}", updatedVendorDTO);
    return ResponseEntity.ok(new Response<>(updatedVendorDTO));
  }

  @Override
  @DeleteMapping("/delete")
  public ResponseEntity<Void> batchDelete(@RequestBody List<Long> ids) {
    log.debug("Deleting vendors: {}", ids);
    vendorManager.batchDelete(ids);
    log.debug("Deleted vendors: {}", ids);
    return ResponseEntity.noContent().build();
  }
}
