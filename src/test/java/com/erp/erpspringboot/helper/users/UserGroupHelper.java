package com.erp.erpspringboot.helper.users;

import com.erp.erpspringboot.core.users.dao.UserGroupDao;
import com.erp.erpspringboot.core.users.mapper.PermissionMapper;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.PermissionDTO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@TestComponent
public class UserGroupHelper {
  @Autowired
  public UserGroupDao userGroupDao;

  @Autowired
  public PermissionMapper permissionMapper;
  @Autowired
  public PermissionHelper permissionHelper;

  public UserGroupBO createDefaultUserGroup() {
    PermissionBO userReadOnly = permissionHelper.createPermission("canReadUser", "user", "read-only");
    PermissionBO userEdit = permissionHelper.createPermission("canEditUser", "user", "edit");
    PermissionBO fullAccess = permissionHelper.createPermission("canDoWhatever", "*", "*");

    return createUserGroup("test-user-group", List.of(userReadOnly, userEdit, fullAccess));
  }

  public UserGroupBO createUserGroup(String name, List<PermissionBO> permissions){
    UserGroupBO userGroupBO = UserGroupBO.builder()
        .name(name)
        .comment("test comment")
        .description("test description")
        .permissions(permissions)
        .build();

    return userGroupDao.save(userGroupBO);
  }



  public List<UserGroupBO> findAll() {
    return userGroupDao.findAll();
  }
}
