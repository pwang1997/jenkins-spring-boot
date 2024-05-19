package com.erp.erpspringboot.core.depots;

import com.erp.erpspringboot.core.depots.dao.InvoiceDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import com.erp.erpspringboot.utils.BOUtils;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@Service
@Transactional
public class InvoiceManager {

  private final InvoiceDao invoiceDao;
  private final DepotManager depotManager;

  public InvoiceManager(InvoiceDao invoiceDao, DepotManager depotManager) {
    this.invoiceDao = invoiceDao;
    this.depotManager = depotManager;
  }

  public InvoiceBO create(InvoiceBO invoiceBO) {
    DepotBO depotBO = depotManager.createIfNotExists(invoiceBO.getDepot());
    depotBO = depotManager.updateDepotQuantity(depotBO.getId(), depotBO.getQuantity());

    invoiceBO.setDepot(depotBO);

    BOUtils.setDirtyFields(invoiceBO);

    return invoiceDao.save(invoiceBO);
  }

  public InvoiceBO update(Long id, InvoiceBO invoiceBO) {
    DepotBO depotBO = depotManager.createIfNotExists(invoiceBO.getDepot());

    InvoiceBO originalInvoiceBO = invoiceDao.findById(id).get();
    long quantityDiff = invoiceBO.getQuantity() - originalInvoiceBO.getQuantity();

    // Update depot quantity in-place for particular depot_in record
    if (quantityDiff != 0) {
      depotManager.updateDepotQuantity(depotBO.getId(), quantityDiff);
    }

    BOUtils.setDirtyFields(invoiceBO);
    return invoiceDao.save(invoiceBO);
  }

  // TODO: doubt if we need delete here
  //  may consider soft delete with depot quantity decrement
  public void delete(Long id) {
  }

  public List<InvoiceBO> list(int pageNumber, int pageSize) {
    return invoiceDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

}
