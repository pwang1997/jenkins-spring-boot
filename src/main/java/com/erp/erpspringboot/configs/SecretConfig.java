package com.erp.erpspringboot.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application-dev.properties")
@Slf4j
public class SecretConfig {

    @Value("${secret.api}")
    private String secretToken;

    @Value("${server.host}")
    private String host;

    @Bean
    public String getHost() {
        return host;
    }

    @Bean
    public String getSecretToken() {
        return secretToken;
    }
}
