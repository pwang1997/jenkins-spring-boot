package com.erp.erpspringboot.utils;

import com.erp.erpspringboot.core.users.model.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Component;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/7/2024
 */

@Component
public class JwtTokenUtils {

  private final String secret = "rx1LDHwiT8p3K7qNwuyeSa9Bp8VipS2N8VipS2NT8p3K7qNw";
  private final long duration = 3_600_000L;

  public String generateToken(UserDTO user) {
    System.out.println(new Date(System.currentTimeMillis() + duration));
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .claims(claims)
        .subject(user.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + duration))
        .signWith(getSigningKey())
        .compact();
  }

  public boolean validateToken(String token, UserDTO user) {
    final String username = extractUsername(token);
    return (username.equals(user.getUsername()) && !isTokenExpired(token));
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
