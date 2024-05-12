package com.erp.erpspringboot.unit.users;

import static com.erp.erpspringboot.utils.PermissionUtils.EDITABLE;

import com.erp.erpspringboot.core.users.dao.PermissionDao;
import com.erp.erpspringboot.core.users.dao.UserGroupDao;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@TestComponent
public class UserGroupTest {

  @Autowired
  public UserGroupDao userGroupDao;

  @Autowired
  public PermissionDao permissionDao;


  @Test
  public void canCreateUserGroup() {
    PermissionBO permissionBO = PermissionBO.builder()
        .name("CanReadInvoice")
        .resource("invoice")
        .comment("test invoice permission")
        .action(EDITABLE)
        .build();
    PermissionBO save = permissionDao.save(permissionBO);

    UserGroupBO userGroupBO = UserGroupBO.builder()
        .permissions(List.of(save))
        .description("test-description")
        .name("test-name")
        .comment("test-comment")
        .build();
    UserGroupBO savedUserGroupBO = userGroupDao.save(userGroupBO);

    Assertions.assertNotNull(savedUserGroupBO);
  }


}
