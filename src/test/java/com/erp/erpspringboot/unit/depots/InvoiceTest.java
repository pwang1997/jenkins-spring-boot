package com.erp.erpspringboot.unit.depots;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.invoices.InvoiceManager;
import com.erp.erpspringboot.core.invoices.model.InvoiceBO;
import com.erp.erpspringboot.exceptions.InsufficientDepotException;
import com.erp.erpspringboot.helper.depots.DepotHelper;
import com.erp.erpspringboot.helper.depots.InvoiceHelper;
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
public class InvoiceTest extends MySQLTestContainer {

  @Autowired
  public DepotHelper depotHelper;
  @Autowired
  public InvoiceHelper invoiceHelper;
  @Autowired
  public InvoiceManager invoiceManager;

  @Test
  @Rollback
  public void canCreateInvoice() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    invoiceHelper.createInvoice(null, defaultDepot);
    List<InvoiceBO> list = invoiceManager.list(0, 100);
    Assertions.assertEquals(list.size(), 1);
  }

  @Test
  @Rollback
  public void canUpdateInvoice() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();

    InvoiceBO defaultInvoice = invoiceHelper.createInvoice(null, defaultDepot);
    defaultInvoice.setQuantity(200L);
    invoiceHelper.updateInvoice(defaultInvoice);

    List<InvoiceBO> list = invoiceManager.list(0, 100);
    InvoiceBO invoiceBO = list.get(0);

    Assertions.assertEquals(invoiceBO.getQuantity(), 200L);
  }

  @Test
  @Rollback
  public void cannotDeleteInvoiceWithInsufficientDepotQuantity() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    InvoiceBO defaultInvoice = invoiceHelper.createInvoice(null, defaultDepot);

    Assertions.assertThrows(InsufficientDepotException.class,
        () -> invoiceHelper.deleteInvoice(defaultInvoice));
  }

  @Test
  @Rollback
  public void canDeleteInvoice() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    defaultDepot.setQuantity(100L);
    depotHelper.updateDepot(defaultDepot);

    InvoiceBO defaultInvoice = invoiceHelper.createInvoice(null, defaultDepot);

    invoiceHelper.deleteInvoice(defaultInvoice);
    Assertions.assertTrue(defaultInvoice.isDeleted());
  }

}
