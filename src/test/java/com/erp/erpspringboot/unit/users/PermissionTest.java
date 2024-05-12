package com.erp.erpspringboot.unit.users;

import static com.erp.erpspringboot.utils.PermissionUtils.READ_ONLY;

import com.erp.erpspringboot.core.users.dao.PermissionDao;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.helper.users.PermissionHelper;
import com.erp.erpspringboot.testContainer.MySQLTestContainer;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@Slf4j
@Transactional
public class PermissionTest extends MySQLTestContainer {
  @Autowired
  public PermissionDao permissionDao;

  @Autowired
  public PermissionHelper permissionHelper;

  @Test
  @Rollback
  public void testCanCreatePermission() {
    PermissionBO defaultPermission = permissionHelper.createDefaultPermission();
    Assertions.assertNotNull(defaultPermission);

    List<PermissionBO> all = permissionDao.findAll();
    Assertions.assertEquals(all.size(), 1);
  }

  @Test
  @Rollback
  public void testCanGetPermissions() {
    permissionHelper.createDefaultPermission();
    permissionHelper.createPermission("permission2", "defaultName", READ_ONLY);
    List<PermissionBO> all = permissionDao.findAll();
    Assertions.assertEquals(all.size(), 2);
  }
}
