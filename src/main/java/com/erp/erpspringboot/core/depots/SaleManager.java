package com.erp.erpspringboot.core.depots;

import com.erp.erpspringboot.core.depots.dao.SaleDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.SaleBO;
import com.erp.erpspringboot.exceptions.InsufficientDepotException;
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
public class SaleManager {

  private final SaleDao saleDao;
  private final DepotManager depotManager;

  public SaleManager(SaleDao saleDao, DepotManager depotManager) {
    this.saleDao = saleDao;
    this.depotManager = depotManager;
  }

  public SaleBO create(SaleBO saleBO) {
    DepotBO depotBO = depotManager.findById(saleBO.getDepot().getId());
    long quantityAfterDepotOut = saleBO.getDepot().getQuantity() - saleBO.getQuantity();
    if (quantityAfterDepotOut < 0) {
      throw new InsufficientDepotException("库存不足");
    }

    depotBO = depotManager.updateDepotQuantity(depotBO.getId(), quantityAfterDepotOut);

    saleBO.setDepot(depotBO);

    BOUtils.setDirtyFields(saleBO);

    return saleDao.save(saleBO);
  }

  public SaleBO update(Long id, SaleBO saleBO) {
    DepotBO depotBO = depotManager.createIfNotExists(saleBO.getDepot());

    SaleBO originalSaleBO = saleDao.findById(id).get();
    long quantityDiff = saleBO.getQuantity() - originalSaleBO.getQuantity();

    // Update depot quantity in-place for particular depot_in record
    if (quantityDiff < 0) {
      throw new InsufficientDepotException("库存不足");
    }
    depotManager.updateDepotQuantity(depotBO.getId(), quantityDiff);

    BOUtils.setDirtyFields(saleBO);
    return saleDao.save(saleBO);
  }

  // TODO: doubt if we need delete here
  //  may consider soft delete with depot quantity decrement
  public void delete(Long id) {
  }

  public List<SaleBO> list(int pageNumber, int pageSize) {
    return saleDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

}
