package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseBO;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class DepotBO extends BaseBO {

  @EmbeddedId
  private DepotId id;

  @Column(nullable = false)
  private Long quantity;

}
