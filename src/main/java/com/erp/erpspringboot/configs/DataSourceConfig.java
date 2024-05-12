package com.erp.erpspringboot.configs;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */
@Configuration
public class DataSourceConfig {

  private final PropertyConfig propertyConfig;

  public DataSourceConfig(PropertyConfig propertyConfig) {
    this.propertyConfig = propertyConfig;
  }

  @Bean
  public DataSource datasource() {
    return DataSourceBuilder.create()
        .driverClassName(propertyConfig.getDriverClassName())
        .url(propertyConfig.getUrl())
        .username(propertyConfig.getUsername())
        .password(propertyConfig.getPassword())
        .build();
  }
}
