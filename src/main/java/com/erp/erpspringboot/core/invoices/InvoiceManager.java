package com.erp.erpspringboot.core.invoices;

import com.erp.erpspringboot.core.depots.DepotManager;
import com.erp.erpspringboot.core.depots.dao.DepotDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.invoices.dao.InvoiceDao;
import com.erp.erpspringboot.core.invoices.model.InvoiceBO;
import com.erp.erpspringboot.exceptions.InsufficientDepotException;
import com.erp.erpspringboot.utils.BOUtils;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class InvoiceManager {

  private final InvoiceDao invoiceDao;
  private final DepotManager depotManager;
  private final DepotDao depotDao;

  public InvoiceManager(InvoiceDao invoiceDao, DepotManager depotManager,
      DepotDao depotDao) {
    this.invoiceDao = invoiceDao;
    this.depotManager = depotManager;
    this.depotDao = depotDao;
  }

  public InvoiceBO create(InvoiceBO invoiceBO) {
    DepotBO depotBO = depotManager.createIfNotExists(invoiceBO.getDepot());

    long overallQuantity = depotBO.getQuantity();
    long updatedOverallQuantity = overallQuantity + invoiceBO.getQuantity();
    DepotBO updatedDepotBO = depotManager.updateDepotQuantity(depotBO.getId(),
        updatedOverallQuantity);

    invoiceBO.setDepot(updatedDepotBO);

    BOUtils.setDirtyFields(invoiceBO);

    return invoiceDao.save(invoiceBO);
  }

  public InvoiceBO update(Long id, InvoiceBO invoiceBO) {
    DepotBO depotBO = depotManager.createIfNotExists(invoiceBO.getDepot());

    InvoiceBO originalInvoiceBO = invoiceDao.findById(id).get();
    long quantityDiff = invoiceBO.getQuantity() - originalInvoiceBO.getQuantity();

    Long overallQuantity = invoiceBO.getDepot().getQuantity();
    // Update depot quantity in-place for particular depot_in record
    if (overallQuantity + quantityDiff < 0) {
      throw new InsufficientDepotException();
    }
    BOUtils.setDirtyFields(invoiceBO);
    InvoiceBO updatedInvoice = invoiceDao.save(invoiceBO);
    depotManager.updateDepotQuantity(depotBO.getId(), overallQuantity + quantityDiff);

    return updatedInvoice;
  }

  public void delete(Long id) {
    InvoiceBO invoiceBO = invoiceDao.findById(id).get();

    DepotBO depot = invoiceBO.getDepot();

    Long overallQuantity = depot.getQuantity();

    if (overallQuantity < invoiceBO.getQuantity()) {
      throw new InsufficientDepotException();
    }
    depotManager.updateDepotQuantity(depot.getId(), overallQuantity - invoiceBO.getQuantity());
    softDelete(id, invoiceBO);
  }

  public List<InvoiceBO> list(int pageNumber, int pageSize) {
    return invoiceDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

  private void softDelete(Long id, InvoiceBO invoiceBO) {
    invoiceBO.setDeleted(true);
    update(id, invoiceBO);
  }

}
