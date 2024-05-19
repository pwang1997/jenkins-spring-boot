package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
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
public class InvoiceDTO extends BaseDTO { // invoice

  private Long id;

  // Depot attributes
  @Valid
  private DepotDTO depot;
  private Long quantity;

  // Invoice attributes
  private String serialNo; // 货号
  private String makeupId; // 批号柜号
  private String reference; // 可溯源编号
  private String spec; // 规格
  private String brand; // 品牌

  //  Product attributes
  private Long weight; // 重量
  private Long pricePerBox; // 箱价
  private Long pricePerWeight; // 斤价
  private Long subtotal; // 总价
  private Boolean isInDebt; // 入库单是否欠账

  @Enumerated(EnumType.STRING)
  private AccountMethodEnum accountMethod; // 计价方式

  @Enumerated(EnumType.STRING)
  private SaleMethodEnum saleMethod; // 销售方式

  //  Internal attributes
  private UserDTO buyer; // 采购
  private UserDTO salesPerson;  // 业务员
  private String carLicense; // 车牌

  private String commentOne;
  private String commentTwo;
}
