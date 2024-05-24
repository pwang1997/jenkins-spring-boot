package com.erp.erpspringboot.utils;

import com.erp.erpspringboot.base.models.BaseBO;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/5/2024
 */

public class BOUtils {

  public static void setDirtyFields(BaseBO bo) {
    ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
    ZonedDateTime shanghaiTime = ZonedDateTime.now(shanghaiZone);
    Instant instant = shanghaiTime.toInstant();
    if (bo.getCreatedAt() == null) {
      bo.setCreatedAt(Timestamp.from(instant));
    }

    bo.setUpdatedAt(Timestamp.from(instant));
  }

}
