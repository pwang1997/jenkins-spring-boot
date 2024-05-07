package com.erp.erpspringboot.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public class SecurityUtils {

  public static String sha256(String str) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hashed = digest.digest(str.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(hashed);
  }
}
