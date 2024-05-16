package com.erp.erpspringboot.core.depots.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.depots.model.DepotInBO;
import com.erp.erpspringboot.core.depots.model.DepotInDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
@Mapper(componentModel = "spring")
public interface DepotInMapper extends BaseMapper<DepotInBO, DepotInDTO> {

}
