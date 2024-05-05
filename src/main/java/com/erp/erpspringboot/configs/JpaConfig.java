package com.erp.erpspringboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.erp.erpspringboot.core")
public class JpaConfig {
}
