package com.erp.erpspringboot.core.depots.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.depots.model.InvoiceBO;
import com.erp.erpspringboot.core.depots.model.InvoiceDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper extends BaseMapper<InvoiceBO, InvoiceDTO> {

}
