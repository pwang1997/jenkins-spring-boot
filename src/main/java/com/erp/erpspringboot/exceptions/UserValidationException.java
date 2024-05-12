package com.erp.erpspringboot.exceptions;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/7/2024
 */
public class UserValidationException extends RuntimeException {
  public UserValidationException(String message) {
    super(message);
  }
}
