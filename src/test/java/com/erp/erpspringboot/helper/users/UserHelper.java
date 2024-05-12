package com.erp.erpspringboot.helper.users;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_USERS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.erp.erpspringboot.core.users.dao.UserDao;
import com.erp.erpspringboot.core.users.mapper.PermissionMapper;
import com.erp.erpspringboot.core.users.mapper.UserGroupMapper;
import com.erp.erpspringboot.core.users.model.PermissionBO;
import com.erp.erpspringboot.core.users.model.PermissionDTO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import com.erp.erpspringboot.core.users.model.UserGroupBO;
import com.erp.erpspringboot.core.users.model.UserGroupDTO;
import com.erp.erpspringboot.utils.JsonUtils;
import java.util.List;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/11/2024
 */

@TestComponent
public class UserHelper {

  @Autowired
  public MockMvc mockMvc;
  @Autowired
  public JsonUtils jsonUtils;
  @Autowired
  public UserDao userDao;
  @Autowired
  public UserGroupHelper userGroupHelper;
  @Autowired
  public PermissionHelper permissionHelper;
  @Autowired
  public UserGroupMapper userGroupMapper;

  public static final String DEFAULT_USERNAME = "default_user";

  public UserDTO createDefaultUserDTO() {
    UserGroupDTO defaultUserGroup = userGroupMapper.mapToDTO(userGroupHelper.createDefaultUserGroup());

    return createDefaultUserDTO(List.of(defaultUserGroup));
  }

  public UserDTO createDefaultUserDTO(List<UserGroupDTO> userGroups) {
    return createUserDTO(DEFAULT_USERNAME, "password", "default-employee",
        "default-department", userGroups);
  }

  public UserDTO createUserDTO(String username, String password, String employeeName,
      String department,
      List<UserGroupDTO> userGroups) {
    return UserDTO.builder()
        .userGroups(userGroups)
        .username(username)
        .password(password)
        .employeeName(employeeName)
        .department(department)
        .isDeleted(false)
        .build();
  }

  @SneakyThrows
  public UserDTO createUser(UserDTO user) {
    String json = jsonUtils.convertToJson(user);
    MvcResult mvcResult = mockMvc.perform(post(API_V1_USERS + "/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.id").isNotEmpty())
        .andReturn();

    String contentAsString = jsonUtils.getResponse(mvcResult);
    String content = jsonUtils.getJsonResponse(contentAsString, "$.data");

    return (UserDTO) jsonUtils.deserialize(content, UserDTO.class);
  }

  @SneakyThrows
  public String getToken(UserDTO user) {
    MvcResult mvcResult = getTokenResult(user);
    String contentAsString = jsonUtils.getResponse(mvcResult);
    return jsonUtils.getJsonResponse(contentAsString, "$.token");
  }

  @SneakyThrows
  public MvcResult getTokenResult(UserDTO user) {
    String json = jsonUtils.convertToJson(user);
    return mockMvc.perform(post(API_V1_USERS + "/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andReturn();
  }

  @SneakyThrows
  public UserDTO updateUser(UserDTO user, String token) {
    MvcResult mvcResult = getUpdateUserResult(user, token);

    System.out.println(mvcResult.getResponse().getContentAsString());
    String contentAsString = jsonUtils.getResponse(mvcResult);
    String content = jsonUtils.getJsonResponse(contentAsString, "$.data");

    return (UserDTO) jsonUtils.deserialize(content, UserDTO.class);
  }

  @SneakyThrows
  public MvcResult getUpdateUserResult(UserDTO user, String token) {
    String json = jsonUtils.convertToJson(user);
    return mockMvc.perform(put(API_V1_USERS + "/update/" + user.getId().toString())
            .header("X-Token", token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andReturn();
  }
}
