package com.erp.erpspringboot.core.users.rest;

import com.erp.erpspringboot.base.models.Response;
import com.erp.erpspringboot.base.models.Responses;
import com.erp.erpspringboot.core.users.model.UserDTO;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/6/2024
 */
public interface UserController {
  ResponseEntity<Response<UserDTO>> register(UserDTO userDTO);

  ResponseEntity<Responses<UserDTO>> list(Integer pageNumber, Integer pageSize);
  ResponseEntity<Response<UserDTO>> update(UUID id, UserDTO userDTO);
  ResponseEntity<Response<UserDTO>> delete(UUID id);

  ResponseEntity<?> login(UserDTO userDTO);
}
