package com.erp.erpspringboot.testContainer;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */
@SpringBootTest
public class MySQLTestContainer {

  @ServiceConnection
  protected static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.1.0")
      .withDatabaseName("testcontainer")
      .withUsername("root")
      .withPassword("password")
      .withTmpFs(Map.of("/var/lib/mysql", "rw"))
      .withReuse(true);

  static {
    mySQLContainer.start();
  }

  @Autowired
  protected DataSource dataSource;

}