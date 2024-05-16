package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import com.erp.erpspringboot.base.models.BaseDTO;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
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
public class DepotOutBO extends BaseBO { // Sales
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "vendor_id")
  private VendorBO vendor;
  @Column(nullable = false)
  private String category;
  @Column(nullable = false)
  private String batch;
  @Column(nullable = false)
  private Long price; // 单价
  private Long quantity; // 数量
  private Long bag; // 包装
  private Long subTotal; // 总额

}
