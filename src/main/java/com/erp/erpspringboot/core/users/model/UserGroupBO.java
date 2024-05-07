package com.erp.erpspringboot.core.users.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@Entity(name = "user_groups")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupBO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String description;
  private String comment;

  @ManyToMany
  @JoinTable(name = "user_group_permission_assn", joinColumns = {
      @JoinColumn(name = "user_group_id")}, inverseJoinColumns = {
      @JoinColumn(name = "permission_id")},
      foreignKey = @ForeignKey(name = "FK_USER_GROUP_ID"), inverseForeignKey = @ForeignKey(name = "FK_PERMISSION_ID"))
  private List<PermissionBO> permissions;
}
