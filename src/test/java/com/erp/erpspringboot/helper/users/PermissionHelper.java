package com.erp.erpspringboot.helper.users;

import static com.erp.erpspringboot.utils.PermissionUtils.EDITABLE;

import com.erp.erpspringboot.core.users.dao.PermissionDao;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@TestComponent
public class PermissionHelper {

  @Autowired
  public PermissionDao permissionDao;

  public PermissionBO createDefaultPermission() {
    return createPermission("CanReadInvoice", "invoice", EDITABLE);
  }

  public PermissionBO createPermission(String name, String resource, String action) {
    PermissionBO permissionBO = PermissionBO.builder()
        .name(name)
        .resource(resource)
        .comment("test permission comment")
        .action(action)
        .build();
    return permissionDao.save(permissionBO);
  }

  public List<PermissionBO> findAll() {
    return permissionDao.findAll();
  }
}
