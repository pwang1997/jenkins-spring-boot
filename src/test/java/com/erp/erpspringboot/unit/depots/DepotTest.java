package com.erp.erpspringboot.unit.depots;

import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.helper.depots.DepotHelper;
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
public class DepotTest extends MySQLTestContainer {

  @Autowired
  public DepotHelper depotHelper;

  @Test
  @Rollback
  public void canCreateDepot() {
    depotHelper.createDefaultDepot();
    List<DepotBO> all = depotHelper.findAll();
    Assertions.assertEquals(all.size(), 1);
  }

  @Test
  @Rollback
  public void canUpdateDepot() {
    DepotBO depotBO = depotHelper.createDefaultDepot();
    depotBO.setQuantity(200L);
    DepotBO updatedDepotBO = depotHelper.updateDepot(depotBO);
    Assertions.assertEquals(updatedDepotBO.getQuantity(), 200L);
  }

  @Test
  @Rollback
  public void canListAllDepots() {
    depotHelper.createDefaultDepot();
    depotHelper.createDefaultDepot();
    List<DepotBO> all = depotHelper.findAll();
    Assertions.assertEquals(all.size(), 2);
  }


}
