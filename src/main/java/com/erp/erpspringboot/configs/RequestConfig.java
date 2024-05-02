package com.erp.erpspringboot.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestConfig implements WebMvcConfigurer {

    private final SecretConfig secretConfig;

    public RequestConfig(SecretConfig secretConfig) {
        this.secretConfig = secretConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HttpRequestInterceptor());
//        registry.addInterceptor(new SecretTokenInterceptor(secretConfig));
    }
}
