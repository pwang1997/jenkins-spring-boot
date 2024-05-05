package com.erp.erpspringboot.core.vendors.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import com.erp.erpspringboot.core.vendors.model.VendorDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

@Mapper(componentModel = "spring")
public interface VendorMapper extends BaseMapper<VendorBO, VendorDTO> {
}
