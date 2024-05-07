package com.erp.erpspringboot.configs;

import com.erp.erpspringboot.interceptors.HttpRequestInterceptor;
import com.erp.erpspringboot.interceptors.TokenValidationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestConfig implements WebMvcConfigurer {


  public RequestConfig() {
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HttpRequestInterceptor());
    registry.addInterceptor(new TokenValidationInterceptor())
        .addPathPatterns("/api/**");
  }
}
