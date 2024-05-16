package com.erp.erpspringboot.core.depots;

import com.erp.erpspringboot.core.depots.dao.DepotInDao;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.DepotInBO;
import com.erp.erpspringboot.utils.BOUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@Service
public class DepotInManager {

  private final DepotInDao depotInDao;
  private final DepotManager depotManager;

  public DepotInManager(DepotInDao depotInDao, DepotManager depotManager) {
    this.depotInDao = depotInDao;
    this.depotManager = depotManager;
  }

  public DepotInBO create(DepotInBO depotInBO) {
    Optional<DepotBO> depotBO = depotManager.findById();
    if (depotBO.isEmpty()) {
      DepotBO depotBOToCreate = DepotBO.builder().id(depotId).quantity(depotInBO.getQuantity())
          .build();
      depotManager.create(depotBOToCreate);
    } else {

    }


    BOUtils.setDirtyFields(depotInBO);

    DepotInBO save = depotInDao.save(depotInBO);
    return save;
  }

  public DepotInBO update(DepotInBO depotInBO) {
    BOUtils.setDirtyFields(depotInBO);
    DepotInBO updated = depotInDao.save(depotInBO);
    return updated;
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
