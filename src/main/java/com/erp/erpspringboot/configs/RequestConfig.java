package com.erp.erpspringboot.configs;

import com.erp.erpspringboot.interceptors.HttpRequestInterceptor;
import com.erp.erpspringboot.interceptors.TokenValidationInterceptor;
import com.erp.erpspringboot.utils.JwtTokenUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestConfig implements WebMvcConfigurer {
  private final JwtTokenUtils jwtTokenUtils;

  public RequestConfig(JwtTokenUtils jwtTokenUtils) {
    this.jwtTokenUtils = jwtTokenUtils;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new HttpRequestInterceptor());
    registry.addInterceptor(new TokenValidationInterceptor(jwtTokenUtils))
        .excludePathPatterns("/api/*/users/register", "/api/*/users/login")
        .addPathPatterns("/api/**");
  }
}
