package com.erp.erpspringboot.exceptions;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/19/2024
 */
public class InsufficientDepotException extends RuntimeException {

  public InsufficientDepotException(String message) {
    super(message);
  }
}
