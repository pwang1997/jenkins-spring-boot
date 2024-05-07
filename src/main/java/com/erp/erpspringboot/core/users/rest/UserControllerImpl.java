package com.erp.erpspringboot.core.users.rest;

import static com.erp.erpspringboot.constant.RestEndpoint.API_V1_USERS;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.users.UserManager;
import com.erp.erpspringboot.core.users.mapper.UserMapper;
import com.erp.erpspringboot.core.users.model.UserBO;
import com.erp.erpspringboot.core.users.model.UserDTO;
import com.erp.erpspringboot.utils.JwtTokenUtils;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */

@RestController
@RequestMapping(API_V1_USERS)
@Validated
@Slf4j
public class UserControllerImpl implements UserController {

  private final UserManager userManager;
  private final UserMapper userMapper;
  private final JwtTokenUtils jwtTokenUtils;

  public UserControllerImpl(UserManager userManager, UserMapper userMapper,
      @Lazy JwtTokenUtils jwtTokenUtils) {
    this.userManager = userManager;
    this.userMapper = userMapper;
    this.jwtTokenUtils = jwtTokenUtils;
  }

  @Override
  @PostMapping("/register")
  public ResponseEntity<Response<UserDTO>> register(@Validated @RequestBody UserDTO userDTO) {
    UserBO userBO = userMapper.mapToBO(userDTO);
    UserBO createdUserBO = userManager.register(userBO);
    UserDTO createdUserDTO = userMapper.mapToDTO(createdUserBO);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdUserDTO.getId())
        .toUri();
    return ResponseEntity.created(location).body(new Response<>(createdUserDTO));
  }

  @Override
  @GetMapping("/list")
  public ResponseEntity<Responses<UserDTO>> list(
      @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber
  ) {
    List<UserBO> userBOs = userManager.list(pageNumber, pageSize).stream()
        .filter(userBO -> !userBO.isDeleted()).toList();
    List<UserDTO> userDTOs = userBOs.stream().map(userMapper::mapToDTO).toList();
    return ResponseEntity.ok(new Responses<>(userDTOs));
  }

  @Override
  @PutMapping("/update/{id}")
  public ResponseEntity<Response<UserDTO>> update(
      @PathVariable(name = "id") UUID id,
      @Validated @RequestBody UserDTO userDTO
  ) {
    UserBO updatedUserBO = userManager.update(id, userMapper.mapToBO(userDTO));
    UserDTO updatedUserDTO = userMapper.mapToDTO(updatedUserBO);
    return ResponseEntity.ok(new Response<>(updatedUserDTO));
  }

  @Override
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response<UserDTO>> delete(@PathVariable(name = "id") UUID id) {
    userManager.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<?> login(@Validated @RequestBody UserDTO userDTO) {
    UserBO userBO = userMapper.mapToBO(userDTO);
    userManager.validateCredentials(userBO.getUsername(), userBO.getPassword());
    Map<String, String> map = Map.of("token", jwtTokenUtils.generateToken(userDTO));
    return ResponseEntity.ok(map);
  }
}
