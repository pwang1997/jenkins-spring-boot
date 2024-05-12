package com.erp.erpspringboot.unit.users;

import static com.erp.erpspringboot.utils.PermissionUtils.EDITABLE;

import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import com.erp.erpspringboot.helper.users.PermissionHelper;
import com.erp.erpspringboot.helper.users.UserGroupHelper;
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
public class UserGroupTest extends MySQLTestContainer {

  @Autowired
  public PermissionHelper permissionHelper;
  @Autowired
  public UserGroupHelper userGroupHelper;

  @Test
  @Rollback
  public void canCreateUserGroup() {
    userGroupHelper.createDefaultUserGroup();
    List<UserGroupBO> all = userGroupHelper.findAll();
    Assertions.assertEquals(all.size(), 1);
  }

  @Test
  @Rollback
  public void canGetUserGroups() {
    PermissionBO defaultPermission = permissionHelper.createDefaultPermission();
    PermissionBO invoicePermission = permissionHelper.createPermission("test-permission-2",
        "invoice",
        EDITABLE);

    userGroupHelper.createUserGroup("test-user-group-1",
        List.of(defaultPermission, invoicePermission));

    userGroupHelper.createUserGroup("test-user-group-2",
        List.of(defaultPermission));
    List<UserGroupBO> all = userGroupHelper.findAll();
    Assertions.assertEquals(all.size(), 2);
  }


}
