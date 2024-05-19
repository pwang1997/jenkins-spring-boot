package com.erp.erpspringboot.core.depots.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_DEPOT_IN;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.depots.InvoiceManager;
import com.erp.erpspringboot.core.depots.mapper.InvoiceMapper;
import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import com.erp.erpspringboot.core.depots.model.InvoiceDTO;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
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
 * @created 5/16/2024
 */

@RestController
@RequestMapping(API_V1_DEPOT_IN)
@Validated
public class InvoiceControllerImpl implements InvoiceController {

  private final InvoiceMapper invoiceMapper;
  private final InvoiceManager invoiceManager;

  public InvoiceControllerImpl(InvoiceMapper invoiceMapper,
      InvoiceManager invoiceManager) {
    this.invoiceMapper = invoiceMapper;
    this.invoiceManager = invoiceManager;
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<InvoiceDTO>> create(@RequestBody @Valid InvoiceDTO depotIn) {
    InvoiceBO invoiceBO = invoiceMapper.mapToBO(depotIn);

    invoiceBO = invoiceManager.create(invoiceBO);

    InvoiceDTO invoiceDTO = invoiceMapper.mapToDTO(invoiceBO);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(invoiceDTO.getId())
        .toUri();
    return ResponseEntity.created(location).body(new Response<>(invoiceDTO));
  }

  @Override
  @GetMapping("/list")
  public ResponseEntity<Responses<InvoiceDTO>> list(
      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
  ) {
    List<InvoiceBO> invoiceBOList = invoiceManager.list(pageNumber, pageSize);
    List<InvoiceDTO> invoiceDTOList = invoiceBOList.stream().map(invoiceMapper::mapToDTO).toList();
    return ResponseEntity.ok(new Responses<>(invoiceDTOList));

  }

  @Override
  @PutMapping("/{id}")
  public ResponseEntity<Response<InvoiceDTO>> update(
      @PathVariable(name = "id") Long id,
      @RequestBody InvoiceDTO depotIn) {
    InvoiceBO invoiceBO = invoiceMapper.mapToBO(depotIn);
    InvoiceBO updated = invoiceManager.update(id, invoiceBO);

    InvoiceDTO updatedInvoice = invoiceMapper.mapToDTO(updated);
    return ResponseEntity.ok(new Response<>(updatedInvoice));
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Response<InvoiceDTO>> delete(@PathVariable(name = "id") Long id) {
    return null;
  }
}
