package com.erp.erpspringboot.core.vendors.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
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
 * @created 5/4/2024
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@SuperBuilder
@JsonInclude
public class VendorDTO extends BaseDTO {

  private Long id;
  @NotEmpty(message = "公司名不能为空")
  private String company;
  @NotEmpty(message = "联系方式不能为空")
  private String contact;
  @Nullable
  private String noteOne;
  @Nullable
  private String noteTwo;

}
