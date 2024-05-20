package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@SuperBuilder
@JsonInclude
public class SaleDTO extends BaseDTO { // Sales

  private Long id;
  private DepotDTO depot;
  private Long quantity; // 本次件数

  private Long price; // 单价
  private Long bag; // 包装
  private Long subtotal; // 总额
  private boolean isDeleted;
}
