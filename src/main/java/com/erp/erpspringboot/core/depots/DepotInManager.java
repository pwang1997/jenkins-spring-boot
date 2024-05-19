package com.erp.erpspringboot.core.depots;

import com.erp.erpspringboot.core.depots.dao.DepotInDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.DepotInBO;
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
public class DepotInManager {

  private final DepotInDao depotInDao;
  private final DepotManager depotManager;

  public DepotInManager(DepotInDao depotInDao, DepotManager depotManager) {
    this.depotInDao = depotInDao;
    this.depotManager = depotManager;
  }

  public DepotInBO create(DepotInBO depotInBO) {
    DepotBO depotBO = depotManager.createIfNotExists(depotInBO.getDepot());

    depotInBO.setDepot(depotBO);

    BOUtils.setDirtyFields(depotInBO);

    return depotInDao.save(depotInBO);
  }

  public DepotInBO update(DepotInBO depotInBO) {
    BOUtils.setDirtyFields(depotInBO);
    return depotInDao.save(depotInBO);
  }

  public void delete(Long id) {
    depotInDao.deleteById(id);
  }

  public List<DepotInBO> list(int pageNumber, int pageSize) {
    return depotInDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

}
