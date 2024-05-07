package com.erp.erpspringboot.core.users.model;

import com.erp.erpspringboot.base.models.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
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
public class UserDTO extends BaseDTO {
  private UUID id;
  @NotEmpty(message = "用户名不能为空")
  private String username;
  @NotEmpty(message = "密码不能为空")
  private String password;

  private String department;
  private String employeeName;
  private String cellphone;
  private String carLicense;
  private String note;
  private boolean isDeleted;

  @Default
  private List<UserGroupDTO> userGroups = List.of();

}
