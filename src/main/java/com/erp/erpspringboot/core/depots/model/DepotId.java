package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.core.vendors.model.VendorBO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Data;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/16/2024
 */

@Embeddable
@Data
public class DepotId implements Serializable {

  @ManyToOne
  @JoinColumn(name = "vendor_id")
  private VendorBO vendor;
  @Column(nullable = false)
  private String category;
  @Column(nullable = false)
  private String batch;
}
