package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/3/2024
 */

@Entity(name = "depots")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepotBO extends BaseBO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "vendor_id")
  private VendorBO vendor; // 供应商
  private String productCategory; // 品名
  private String batch; // 批次
  @Column(columnDefinition = "INT NOT NULL DEFAULT 0")
  private Long quantity; // 件数
}