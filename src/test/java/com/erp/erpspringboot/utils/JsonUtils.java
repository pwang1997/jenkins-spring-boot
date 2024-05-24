package com.erp.erpspringboot.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JsonOrgJsonProvider;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@Component
public class JsonUtils {

  @Autowired
  private ObjectMapper objectMapper;

  @SneakyThrows
  public String convertToJson(Object object) {
    return objectMapper.writeValueAsString(object);
  }

  @SneakyThrows
  public Object deserialize(String json, Class<?> clazz) {
    return objectMapper.readValue(json, clazz);
  }

  @SneakyThrows
  public String getResponse(MvcResult result) {
    return result.getResponse().getContentAsString();
  }

  @SneakyThrows
  public String getJsonResponse(String jsonStr, String jsonPath) {
    JSONObject jsonObject = new JSONObject(jsonStr);
    Configuration configuration = Configuration.builder()
        .jsonProvider(new JsonOrgJsonProvider())
        .build();
    JsonPath jPath = JsonPath.compile(jsonPath);
    return jPath.read(jsonObject, configuration).toString();
  }
}
