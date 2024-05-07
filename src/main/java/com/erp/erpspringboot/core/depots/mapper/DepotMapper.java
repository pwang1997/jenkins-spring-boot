package com.erp.erpspringboot.core.depots.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.depots.model.DepotBO;
import com.erp.erpspringboot.core.depots.model.DepotDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
@Mapper(componentModel = "spring")
public interface DepotMapper extends BaseMapper<DepotBO, DepotDTO> {

}
