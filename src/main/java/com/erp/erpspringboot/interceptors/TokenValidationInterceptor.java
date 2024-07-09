package com.erp.erpspringboot.interceptors;

import com.erp.erpspringboot.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/7/2024
 */

@Component
public class TokenValidationInterceptor  implements HandlerInterceptor {

  private final JwtTokenUtils jwtTokenUtils;

  public TokenValidationInterceptor(JwtTokenUtils jwtTokenUtils) {
    this.jwtTokenUtils = jwtTokenUtils;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    // Get the Authorization header value (Bearer token)
    String token = request.getHeader("X-Token");
    // Validate the token (you can implement your token validation logic here)
    if (isValidToken(request, token)) {
      return true; // Allow the request to proceed
    } else {
      // Set response status code to unauthorized if token is invalid
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false; // Stop the request from proceeding
    }
  }

  private boolean isValidToken(HttpServletRequest request, String token) {
    String method = request.getMethod();
    String requestURI = request.getRequestURI();

    return jwtTokenUtils.validateToken(method, requestURI, token);
  }
}
