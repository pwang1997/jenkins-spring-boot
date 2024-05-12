package com.erp.erpspringboot.testContainer;

import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import java.util.Map;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestPropertySource(locations = {
    "classpath:application-test.properties"
})
@ComponentScan(basePackages = {"com.erp.erpspringboot.core.*", "com.erp.erpspringboot.helper.*",
    "com.erp.erpspringboot.utils.*"})
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class MySQLTestContainer {

  private static final String CONTAINER_VERSION = "mysql:8.1.0";

  @Container
  protected static final MySQLContainer mySQLContainer = new MySQLContainer<>(CONTAINER_VERSION)
      .withDatabaseName("testdb")
      .withUsername("root")
      .withPassword("password")
      .withTmpFs(Map.of("/var/lib/mysql", "rw"))
      .withExposedPorts(3306)
      .withReuse(true);

  static {
    System.setProperty(ACTIVE_PROFILES_PROPERTY_NAME, "TEST");
    mySQLContainer.start();
  }

  @Autowired
  protected DataSource dataSource;

  @Test
  public void testContainerRunning() {
    Assertions.assertTrue(mySQLContainer.isRunning());
  }
}