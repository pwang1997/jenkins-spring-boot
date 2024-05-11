package com.erp.erpspringboot.utils;

import com.erp.erpspringboot.core.users.UserManager;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.UserBO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/7/2024
 */

@Component
public class JwtTokenUtils {

  private final String secret = "rx1LDHwiT8p3K7qNwuyeSa9Bp8VipS2N8VipS2NT8p3K7qNw";
  private final long EXPIRED_AFTER_EIGHT_HOURS = 8 * 3_600_000L;
  private final UserManager userManager;

  public JwtTokenUtils(UserManager userManager) {
    this.userManager = userManager;
  }

  public String generateToken(UserDTO user) {
    System.out.println(new Date(System.currentTimeMillis() + EXPIRED_AFTER_EIGHT_HOURS));
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + EXPIRED_AFTER_EIGHT_HOURS))
        .signWith(getSigningKey())
        .compact();
  }

  public boolean validateToken(String method, String requestURI, String token) {
    final String username = extractUsername(token);
    UserBO userBO = userManager.findByUsername(username);

    return validateToken(token, userBO) && hasValidPermission(method, requestURI, userBO);
  }

  private boolean validateToken(String token, UserBO user) {
    final String username = extractUsername(token);
    return (username.equals(user.getUsername()) && !isTokenExpired(token));
  }

  private boolean hasValidPermission(String method, String requestURI, UserBO userBO) {
    List<UserGroupBO> userGroups = userBO.getUserGroups();
    List<UserGroupBO> userGroupsWithValidPermission = userGroups.stream()
        .filter(userGroupBO -> hasValidPermission(method, requestURI, userGroupBO))
        .toList();
    return CollectionUtils.isNotEmpty(userGroupsWithValidPermission);
  }

  private boolean hasValidPermission(String method, String requestURI, UserGroupBO userGroupBO) {
    List<PermissionBO> permissions = userGroupBO.getPermissions();

    List<PermissionBO> permissionList = permissions.stream()
        .filter(permission -> requestURI.contains(permission.getResource()))
        .filter(permission -> PermissionUtils.REST_METHOD_PERMISSION_ACTION_MAP.get(method)
            .equals(permission.getAction()))
        .toList();

    return CollectionUtils.isNotEmpty(permissionList);
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .setSigningKey(secret)
        .build().parseSignedClaims(token).getPayload();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(this.secret);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
