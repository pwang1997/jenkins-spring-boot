package com.erp.erpspringboot.core.users.mapper;

import com.erp.erpspringboot.core.users.model.UserGroupBO;
import com.erp.erpspringboot.core.users.model.UserGroupDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@Mapper(componentModel = "spring")
public interface UserGroupMapper {
  UserGroupDTO mapToDTO(UserGroupBO b);

  UserGroupBO mapToBO(UserGroupDTO d);
}
