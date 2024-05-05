package com.erp.erpspringboot.utils;

import com.erp.erpspringboot.base.models.BaseBO;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/5/2024
 */

public class BOUtils {

  public static void setDirtyFields(BaseBO bo) {
    if (bo.getCreatedAt() == null) {
      bo.setCreatedAt(Timestamp.from(Instant.now()));
    }

    bo.setUpdatedAt(Timestamp.from(Instant.now()));
  }

}
