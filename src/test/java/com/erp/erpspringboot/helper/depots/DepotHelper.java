package com.erp.erpspringboot.helper.depots;

import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import com.erp.erpspringboot.helper.vendors.VendorHelper;
import com.erp.erpspringboot.utils.JsonUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/24/2024
 */

@TestComponent
public class DepotHelper {

  @Autowired
  public DepotManager depotManager;
  @Autowired
  public VendorHelper vendorHelper;

  public DepotBO createDefaultDepotBO() {
    VendorBO defaultVendor = vendorHelper.createDefaultVendor();
    return createDefaultDepotBO("test-batch-1", "test-product-category", 100L, defaultVendor);
  }

  public DepotBO createDefaultDepotBO(String batch, String category, Long quantity,
      VendorBO vendorBO) {
    return DepotBO.builder()
        .batch(batch)
        .productCategory(category)
        .vendor(vendorBO)
        .quantity(quantity)
        .build();
  }

  public DepotBO createDepot(DepotBO depotBO) {
    return depotManager.create(depotBO);
  }

  public DepotBO createDefaultDepot() {
    return createDepot(createDefaultDepotBO());
  }

  public DepotBO updateDepot(DepotBO depotBO) {
    return depotManager.update(depotBO);
  }

  public List<DepotBO> findAll() {
    return depotManager.list(0, 1000);
  }
}
