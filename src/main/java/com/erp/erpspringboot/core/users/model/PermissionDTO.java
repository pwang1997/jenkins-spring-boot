package com.erp.erpspringboot.core.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@Data
@Validated
@Builder
@JsonInclude
public class PermissionDTO implements Serializable {
  private Long id;
  @NotEmpty(message = "权限名不能为空")
  private String name;
  private String action;
  private String resource;
  private String comment;
}
