package com.erp.erpspringboot.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public class ExceptionUtils {


  public static Map<String, String> getValidationMessages(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getFieldErrors();
    Map<String, String> validationMessages = new HashMap<>();

    for (FieldError fe : fieldErrors) {
      validationMessages.put(fe.getField(), fe.getDefaultMessage());
    }

    return validationMessages;
  }
}
