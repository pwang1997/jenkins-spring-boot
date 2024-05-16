package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.erp.erpspringboot.core.vendors.model.VendorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
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
public class DepotInDTO extends BaseDTO { // invoice

  private Long id;
  private String serialNumber; // 货号
  private String category;
  private String batchId;
  private String traceId;
  //    到货属性
  private String specification;
  private String batch;

  private String brand;
  //    商品属性
  private Integer quantity;
  private Long weight;
  private Long boxPrice;
  private Long weightPrice;
  private Long subtotal;
  //    对外属性
  private VendorDTO vendor;

  @Column
  private boolean isDept;

  private String accountMethod;
  private String saleMethod;
  //    对内属性
//    private PurchasingAgentBO purchasingAgentBO;
//    private SalesAgentBO salesAgent;
//    private String carLicense;
  // 备注
  private String comment1;
  private String comment2;
}
