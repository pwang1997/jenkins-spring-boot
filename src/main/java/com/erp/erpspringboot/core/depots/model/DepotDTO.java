package com.erp.erpspringboot.core.depots.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.erp.erpspringboot.core.vendors.model.VendorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@SuperBuilder
@JsonInclude
public class DepotDTO extends BaseDTO {

  private Long id;
  @NotEmpty
  private VendorDTO vendor;
  @NotEmpty
  private String productCategory;
  @NotEmpty
  private String batch;
  @NotEmpty
  private Long quantity;
}
