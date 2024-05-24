package com.erp.erpspringboot.unit.depots;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.sales.model.SaleBO;
import com.erp.erpspringboot.exceptions.InsufficientDepotException;
import com.erp.erpspringboot.helper.depots.DepotHelper;
import com.erp.erpspringboot.helper.depots.InvoiceHelper;
import com.erp.erpspringboot.helper.depots.SalesHelper;
import com.erp.erpspringboot.testContainer.MySQLTestContainer;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/24/2024
 */
@Slf4j
@Transactional
public class SalesTest extends MySQLTestContainer {

  @Autowired
  public SalesHelper salesHelper;
  @Autowired
  public InvoiceHelper invoiceHelper;
  @Autowired
  public DepotHelper depotHelper;


  @Test
  @Rollback
  public void canCreateSale() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    defaultDepot.setQuantity(100L);
    depotHelper.updateDepot(defaultDepot);

    salesHelper.createDefaultSale(defaultDepot);

    List<SaleBO> list = salesHelper.list(0, 1000);
    Assertions.assertFalse(list.isEmpty());
  }

  @Test
  @Rollback
  public void cannotCreateSaleExceedOverallQuantity() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();

    Assertions.assertThrows(InsufficientDepotException.class,
        () -> salesHelper.createDefaultSale(defaultDepot));
  }

  @Test
  @Rollback
  public void canUpdateSale() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    defaultDepot.setQuantity(100L);
    depotHelper.updateDepot(defaultDepot);

    SaleBO defaultSale = salesHelper.createDefaultSale(defaultDepot);
    defaultSale.setQuantity(75L);
    salesHelper.updateSaleBO(defaultSale);

    SaleBO saleBO = salesHelper.list(0, 1000).get(0);

    Assertions.assertEquals(saleBO.getQuantity(), 75L);
  }

  @Test
  @Rollback
  public void canDeleteSale() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    defaultDepot.setQuantity(100L);
    depotHelper.updateDepot(defaultDepot);
    SaleBO defaultSale = salesHelper.createDefaultSale(defaultDepot);

    salesHelper.deleteSaleBO(defaultSale.getId());

    SaleBO saleBO = salesHelper.list(0, 1000).get(0);

    Assertions.assertTrue(saleBO.isDeleted());
  }

}
