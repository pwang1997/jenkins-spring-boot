package com.erp.erpspringboot.utils;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Map;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

public class PermissionUtils {

  public static final String READ_ONLY = "read-only";
  public static final String EDITABLE = "edit";

  public static final Map<String, String> REST_METHOD_PERMISSION_ACTION_MAP =
      Map.of(GET.name(), READ_ONLY,
          POST.name(), EDITABLE,
          DELETE.name(), EDITABLE,
          PUT.name(), EDITABLE,
          OPTIONS.name(), EDITABLE);
}
