package com.erp.erpspringboot.helper.depots;

import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.model.AccountMethodEnum;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.invoices.InvoiceManager;
import com.erp.erpspringboot.core.invoices.dao.InvoiceDao;
import com.erp.erpspringboot.core.invoices.model.InvoiceBO;
import com.erp.erpspringboot.core.sales.model.SaleMethodEnum;
import com.erp.erpspringboot.core.users.model.UserBO;
import com.erp.erpspringboot.helper.depots.DepotHelper;
import com.erp.erpspringboot.helper.users.UserHelper;
import com.erp.erpspringboot.helper.vendors.VendorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/24/2024
 */
@TestComponent
public class InvoiceHelper {

  @Autowired
  public DepotManager depotManager;
  @Autowired
  public VendorHelper vendorHelper;
  @Autowired
  public DepotHelper depotHelper;
  @Autowired
  public UserHelper userHelper;
  @Autowired
  public InvoiceManager invoiceManager;


  public InvoiceBO createDefaultInvoiceBO(UserBO defaultUser, DepotBO depotBO) {
    return InvoiceBO.builder()
        .depot(depotBO)
        .brand("test-brand")
        .reference("test-reference")
        .serialNo("test-serialNo")
        .accountMethod(AccountMethodEnum.PER_BOX)
        .saleMethod(SaleMethodEnum.DIRECT_SELLING)
        .buyer(defaultUser)
        .salesPerson(defaultUser)
        .makeupId("test-makeupId")
        .pricePerBox(100L)
        .pricePerWeight(10L)
        .weight(1L)
        .quantity(100L)
        .subtotal(1000L)
        .isInDebt(false)
        .build();
  }

  public InvoiceBO createDefaultInvoice() {
    DepotBO defaultDepot = depotHelper.createDefaultDepot();
    InvoiceBO defaultInvoiceBO = createDefaultInvoiceBO(null, defaultDepot);
    return invoiceManager.create(defaultInvoiceBO);
  }

  public InvoiceBO createInvoice(UserBO userBO, DepotBO depotBO) {
    InvoiceBO defaultInvoiceBO = createDefaultInvoiceBO(userBO, depotBO);
    return invoiceManager.create(defaultInvoiceBO);
  }

  public InvoiceBO updateInvoice(InvoiceBO invoiceBO) {
    return invoiceManager.update(invoiceBO.getId(), invoiceBO);
  }

  public void deleteInvoice(InvoiceBO invoiceBO) {
    invoiceManager.delete(invoiceBO.getId());
  }

}
