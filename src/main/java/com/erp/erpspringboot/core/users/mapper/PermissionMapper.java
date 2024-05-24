package com.erp.erpspringboot.core.users.mapper;

import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.PermissionDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
@Mapper(componentModel = "spring")
public interface PermissionMapper {
  PermissionDTO mapToDTO(PermissionBO b);

  PermissionBO mapToBO(PermissionDTO d);
}
