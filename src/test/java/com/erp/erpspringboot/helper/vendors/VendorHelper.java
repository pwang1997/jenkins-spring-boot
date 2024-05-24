package com.erp.erpspringboot.helper.vendors;

import com.erp.erpspringboot.core.vendors.VendorManager;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import com.erp.erpspringboot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/24/2024
 */

@TestComponent
public class VendorHelper {

  @Autowired
  public MockMvc mockMvc;
  @Autowired
  public JsonUtils jsonUtils;

  @Autowired
  public VendorManager vendorManager;


  public VendorBO createDefaultVendorBO() {
    return VendorBO.builder()
        .contact("test-contact")
        .company("test-company")
        .build();
  }

  public VendorBO createDefaultVendor() {
    return vendorManager.create(createDefaultVendorBO());
  }
}
