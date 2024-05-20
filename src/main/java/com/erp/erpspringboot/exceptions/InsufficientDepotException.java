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

  public InsufficientDepotException() {
    this("该操作将会导致库存不足，执行失败。");
  }
}
