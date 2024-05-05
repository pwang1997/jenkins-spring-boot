package com.erp.erpspringboot.base.models;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseDTO implements Serializable {

  private Timestamp createdAt;
  @Builder.Default
  private Timestamp updatedAt = Timestamp.from(Instant.now());
}
