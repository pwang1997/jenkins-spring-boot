package com.erp.erpspringboot.core.users.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.users.model.UserBO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<UserBO, UserDTO> {

}
