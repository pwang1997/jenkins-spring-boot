package com.erp.erpspringboot.integration.users;

import static com.erp.erpspringboot.utils.PermissionUtils.EDITABLE;
import static com.erp.erpspringboot.utils.PermissionUtils.READ_ONLY;

import com.erp.erpspringboot.core.users.dao.UserDao;
import com.erp.erpspringboot.core.users.mapper.UserGroupMapper;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import com.erp.erpspringboot.core.users.model.UserGroupDTO;
import com.erp.erpspringboot.helper.users.PermissionHelper;
import com.erp.erpspringboot.helper.users.UserGroupHelper;
import com.erp.erpspringboot.helper.users.UserHelper;
import com.erp.erpspringboot.testContainer.MySQLTestContainer;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@Slf4j
@Transactional
public class UserIT extends MySQLTestContainer {

  @Autowired
  public UserHelper userHelper;
  @Autowired
  public UserGroupHelper userGroupHelper;
  @Autowired
  public PermissionHelper permissionHelper;
  @Autowired
  public UserDao userDao;
  @Autowired
  public UserGroupMapper userGroupMapper;

  @Test
  @Rollback
  public void canCreateUser() {
    UserDTO defaultUserDTO = userHelper.createDefaultUserDTO();
    UserDTO user = userHelper.createUser(defaultUserDTO);
    Assertions.assertNotNull(user.getId());
    Assertions.assertNotEquals(user.getPassword(), defaultUserDTO.getPassword());
  }

  @Test
  @Rollback
  public void canCreateUserWithUserGroups() {
    PermissionBO invoiceReadOnly = permissionHelper.createPermission("canReadInvoice", "invoice",
        READ_ONLY);
    PermissionBO invoiceEdit = permissionHelper.createPermission("canEditInvoice", "invoice",
        EDITABLE);
    PermissionBO storageReadOnly = permissionHelper.createPermission("canReadStorage", "storage",
        READ_ONLY);

    UserGroupBO userGroup = userGroupHelper.createUserGroup("admin",
        List.of(invoiceReadOnly, invoiceEdit, storageReadOnly));
    UserGroupDTO userGroupDTO = userGroupMapper.mapToDTO(userGroup);

    UserDTO defaultUserDTO = userHelper.createDefaultUserDTO(List.of(userGroupDTO));
    UserDTO user = userHelper.createUser(defaultUserDTO);
    Assertions.assertNotNull(user.getId());
    Assertions.assertNotEquals(user.getPassword(), defaultUserDTO.getPassword());
  }

  @Test
  @Rollback
  public void canValidateUserCredential() {
    UserDTO defaultUserDTO = userHelper.createDefaultUserDTO();
    UserDTO user = userHelper.createUser(defaultUserDTO);

    Assertions.assertEquals(userHelper.getTokenResult(user).getResponse().getStatus(),
        HttpServletResponse.SC_BAD_REQUEST);

    user.setPassword("password");
    Assertions.assertNotNull(userHelper.getToken(user));
  }
  @Test
  @Rollback
  public void canValidateUserCredentialWithWildcard() {
    UserDTO defaultUserDTO = userHelper.createAdminUserDTO();
    UserDTO createdUserDTO = userHelper.createUser(defaultUserDTO);
    String oldPassword = createdUserDTO.getPassword(); // hashed password
    createdUserDTO.setPassword("password");
    String token = userHelper.getToken(createdUserDTO);

    createdUserDTO.setPassword("newPassword");
    createdUserDTO.setEmployeeName("updated-employee-name");

    UserDTO updatedUserDTO = userHelper.updateUser(createdUserDTO, token);
    Assertions.assertNotEquals(updatedUserDTO.getPassword(), oldPassword);
    Assertions.assertEquals(updatedUserDTO.getEmployeeName(), "updated-employee-name");
  }


  @Test
  @Rollback
  public void cannotUpdateUserWithMissingToken() {
    UserDTO defaultUserDTO = userHelper.createDefaultUserDTO();
    UserDTO createdUserDTO = userHelper.createUser(defaultUserDTO);

    createdUserDTO.setPassword("newPassword");
    Assertions.assertEquals(
        userHelper.getUpdateUserResult(createdUserDTO, "unauthorized_token").getResponse()
            .getStatus(),
        HttpServletResponse.SC_UNAUTHORIZED);
  }

  @SneakyThrows
  @Test
  @Rollback
  public void canUpdateUser() {
    UserDTO defaultUserDTO = userHelper.createDefaultUserDTO();
    UserDTO createdUserDTO = userHelper.createUser(defaultUserDTO);
    String oldPassword = createdUserDTO.getPassword(); // hashed password
    createdUserDTO.setPassword("password");
    String token = userHelper.getToken(createdUserDTO);

    createdUserDTO.setPassword("newPassword");
    createdUserDTO.setEmployeeName("updated-employee-name");

    UserDTO updatedUserDTO = userHelper.updateUser(createdUserDTO, token);
    Assertions.assertNotEquals(updatedUserDTO.getPassword(), oldPassword);
    Assertions.assertEquals(updatedUserDTO.getEmployeeName(), "updated-employee-name");
  }

  @Nested
  public class Delete {

  }
}
