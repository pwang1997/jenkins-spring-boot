package com.erp.erpspringboot.core.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
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
public class UserGroupDTO implements Serializable {

  private Long id;
  private String name;
  private String description;
  private String comment;
  @Default
  private List<PermissionDTO> permissions = List.of();
}
