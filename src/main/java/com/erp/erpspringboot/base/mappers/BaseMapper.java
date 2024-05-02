package com.erp.erpspringboot.base.mappers;

import com.erp.erpspringboot.base.models.BaseBO;
import com.erp.erpspringboot.base.models.BaseDTO;

public interface BaseMapper<B extends BaseBO, D extends BaseDTO> {
    D mapToDTO(B b);

    B mapToBO(D d);
}