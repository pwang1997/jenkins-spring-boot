package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import com.erp.erpspringboot.core.users.model.UserBO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */
@Entity(name = "depot_ins")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepotInBO extends BaseBO { // invoice

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Depot attributes
  @ManyToOne
  @JoinColumn(name = "depot_id")
  private DepotBO depot;

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
  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private UserBO buyer; // 采购
  @ManyToOne
  @JoinColumn(name = "sales_person_id")
  private UserBO salesPerson;  // 业务员
  private String carLicense; // 车牌

  private String commentOne;
  private String commentTwo;
}
