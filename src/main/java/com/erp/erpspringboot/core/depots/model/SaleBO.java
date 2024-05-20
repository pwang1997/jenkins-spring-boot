package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity(name = "depot_outs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SaleBO extends BaseBO { // Sales

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "depot_id")
  private DepotBO depot;
  private Long quantity; // 本次件数

  @Column(nullable = false)
  private Long price; // 单价
  private Long bag; // 包装
  private Long subtotal; // 总额
  private boolean isDeleted;

}
