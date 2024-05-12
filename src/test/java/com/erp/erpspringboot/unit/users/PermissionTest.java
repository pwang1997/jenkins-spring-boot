package com.erp.erpspringboot.unit.users;

import static com.erp.erpspringboot.utils.PermissionUtils.READ_ONLY;

import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.helper.users.PermissionHelper;
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
 * @created 5/11/2024
 */

@Slf4j
@Transactional
public class PermissionTest extends MySQLTestContainer {

  @Autowired
  public PermissionHelper permissionHelper;

  @Test
  @Rollback
  public void canCreatePermission() {
    PermissionBO defaultPermission = permissionHelper.createDefaultPermission();
    Assertions.assertNotNull(defaultPermission);

    List<PermissionBO> all = permissionHelper.findAll();
    Assertions.assertEquals(all.size(), 1);
  }

  @Test
  @Rollback
  public void canGetPermissions() {
    permissionHelper.createDefaultPermission();
    permissionHelper.createPermission("permission2", "defaultName", READ_ONLY);
    List<PermissionBO> all = permissionHelper.findAll();
    Assertions.assertEquals(all.size(), 2);
  }
}
