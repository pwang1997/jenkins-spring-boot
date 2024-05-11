package com.erp.erpspringboot.testContainer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@SpringBootTest
public class MySQLContainerClassTest extends MySQLTestContainer {
  @Test
  public void testMySQLContainerIsRunning() {
    assertThat(mySQLContainer.isRunning()).isTrue();
  }
}