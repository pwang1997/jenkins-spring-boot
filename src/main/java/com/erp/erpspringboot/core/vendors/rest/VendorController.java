package com.erp.erpspringboot.core.vendors.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.vendors.model.VendorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

public interface VendorController {
    ResponseEntity<Responses<VendorDTO>> list(Integer pageNumber, Integer pageSize);

    ResponseEntity<Response<VendorDTO>> create(VendorDTO vendorDTO);
    ResponseEntity<Response<VendorDTO>> update(Long id, VendorDTO vendorDTO);
    ResponseEntity<Void> batchDelete(List<Long> ids);
}
