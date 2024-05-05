package com.erp.erpspringboot.core.vendors.model;

import com.erp.erpspringboot.base.models.BaseBO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

@Entity(name = "vendors")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VendorBO extends BaseBO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String company;
  @Column
  private String contact;
  @Column
  private String noteOne;
  @Column
  private String noteTwo;

  @Override
  public final boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    Class<?> oEffectiveClass =
        object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer()
            .getPersistentClass() : object.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
            .getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    VendorBO vendorBO = (VendorBO) object;
    return getId() != null && Objects.equals(getId(), vendorBO.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
        .getPersistentClass().hashCode() : getClass().hashCode();
  }
}
