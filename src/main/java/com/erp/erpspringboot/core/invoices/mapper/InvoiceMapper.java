package com.erp.erpspringboot.core.invoices.mapper;

import com.erp.erpspringboot.base.mappers.BaseMapper;
import com.erp.erpspringboot.core.invoices.model.InvoiceBO;
import com.erp.erpspringboot.core.invoices.model.InvoiceDTO;
import org.mapstruct.Mapper;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
@Mapper(componentModel = "spring")
public interface InvoiceMapper extends BaseMapper<InvoiceBO, InvoiceDTO> {

}
