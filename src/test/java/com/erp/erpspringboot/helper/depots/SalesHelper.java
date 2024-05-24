package com.erp.erpspringboot.helper.depots;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.sales.SaleManager;
import com.erp.erpspringboot.core.sales.model.SaleBO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/24/2024
 */

@TestComponent
public class SalesHelper {

  @Autowired
  public SaleManager saleManager;


  public SaleBO createSale(SaleBO saleBO) {
    return saleManager.create(saleBO);
  }

  public SaleBO defaultSaleBO(DepotBO depotBO) {
    return SaleBO.builder()
        .depot(depotBO)
        .price(1L)
        .quantity(50L)
        .bag(10L)
        .build();
  }

  public SaleBO createDefaultSale(DepotBO depotBO) {
    return createSale(defaultSaleBO(depotBO));
  }

  public SaleBO updateSaleBO(SaleBO saleBO) {
    return saleManager.update(saleBO.getId(), saleBO);
  }

  public void deleteSaleBO(Long id) {
    saleManager.delete(id);
  }

  public List<SaleBO> list(int pageNumber, int pageSize) {
    return saleManager.list(pageNumber, pageSize);
  }
}
