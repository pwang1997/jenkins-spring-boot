package com.erp.erpspringboot.core.users.model;

import com.erp.erpspringboot.base.models.BaseBO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */


@Entity(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserBO extends BaseBO {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "username", columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
  private String username;
  private String password;

  private String department;
  @Column(name = "employee_name", columnDefinition = "VARCHAR(255) NOT NULL UNIQUE")
  private String employeeName;
  private String cellphone;
  private String carLicense;
  private String note;
  @Column(columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
  private boolean isDeleted;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_user_group_assn", joinColumns = {
      @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "user_group_id")},
      foreignKey = @ForeignKey(name = "FK_USER_ID"), inverseForeignKey = @ForeignKey(name = "FK_USER_GROUP_ID"))
  private List<UserGroupBO> userGroups;

  @Override
  public final boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    Class<?> oEffectiveClass = object instanceof HibernateProxy
        ? ((HibernateProxy) object).getHibernateLazyInitializer()
        .getPersistentClass() : object.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    UserBO userBO = (UserBO) object;
    return getId() != null && Objects.equals(getId(), userBO.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
