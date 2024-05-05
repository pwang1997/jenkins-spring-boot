package com.erp.erpspringboot.base.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseBO implements Serializable {

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Timestamp updatedAt;
}
