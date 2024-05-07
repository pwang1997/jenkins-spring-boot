package com.erp.erpspringboot.core.users;

import com.erp.erpspringboot.core.users.dao.UserDao;
import com.erp.erpspringboot.core.users.model.UserBO;
import com.erp.erpspringboot.utils.BOUtils;
import com.erp.erpspringboot.utils.SecurityUtils;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@Service
public class UserManager {

  private final UserDao userDao;

  public UserManager(UserDao userDao) {
    this.userDao = userDao;
  }

  @SneakyThrows
  public UserBO register(UserBO userBO) {
    String hashedPassword = SecurityUtils.sha256(userBO.getPassword());
    userBO.setPassword(hashedPassword);
    BOUtils.setDirtyFields(userBO);
    return userDao.save(userBO);
  }

  @SneakyThrows
  @Transactional
  public void validateCredentials(String username, String password) {
    UserBO userBO = userDao.findByUsername(username);
    if (ObjectUtils.isEmpty(userBO)) {
      throw new EntityNotFoundException("用户名:  " + username + "不存在");
    }

    String hashedPassword = SecurityUtils.sha256(password);

    if (!hashedPassword.equals(userBO.getPassword())) {
      throw new RuntimeException("用户名/密码错误");
    }
  }

  public List<UserBO> list(int pageNumber, int pageSize) {
    return userDao.findAll(
            PageRequest.of(pageNumber, pageSize)
        )
        .toList();
  }

  @Transactional
  public void delete(UUID id) {
    Optional<UserBO> optionalUserBO = userDao.findById(id);

    if (optionalUserBO.isEmpty()) {
      throw new EntityNotFoundException("该用户不存在");
    }

    UserBO userBO = optionalUserBO.get();
    userBO.setDeleted(true);
    userDao.saveAndFlush(userBO);
  }

  @SneakyThrows
  @Transactional
  public UserBO update(UUID id, UserBO userBO) {
    BOUtils.setDirtyFields(userBO);
    String hashedPassword = SecurityUtils.sha256(userBO.getPassword());
    userBO.setPassword(hashedPassword);
    return userDao.save(userBO);
  }
}
