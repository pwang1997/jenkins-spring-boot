package com.erp.erpspringboot.core.depots;

import com.erp.erpspringboot.core.depots.dao.DepotDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import jakarta.persistence.EntityNotFoundException;
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
public class DepotManager {

  private final DepotDao depotDao;

  public DepotManager(DepotDao depotDao) {
    this.depotDao = depotDao;
  }

  public DepotBO create(DepotBO depotBO) {
    return depotDao.saveAndFlush(depotBO);
  }

  public DepotBO update(DepotBO depotBO) {
    return depotDao.saveAndFlush(depotBO);
  }

  public List<DepotBO> list(int pageNumber, int pageSize) {
    return depotDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

  public DepotBO findById(Long id) {
    return depotDao.findById(id).orElseThrow(() -> new EntityNotFoundException("仓储账单不存在"));
  }


  public DepotBO createIfNotExists(DepotBO depotBO) {
    if (depotBO.getId() == null) {
      return this.create(depotBO);
    }
    return findById(depotBO.getId());
  }

  public DepotBO updateDepotQuantity(Long id, Long quantity) {
    depotDao.updateQuantityById(id, quantity);
    depotDao.flush();
    return depotDao.findById(id).get();
  }
}
