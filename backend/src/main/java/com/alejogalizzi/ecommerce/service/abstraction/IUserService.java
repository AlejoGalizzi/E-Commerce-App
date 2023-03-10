package com.alejogalizzi.ecommerce.service.abstraction;

import com.alejogalizzi.ecommerce.model.dto.UserDTO;

public interface IUserService {

  UserDTO register(UserDTO userDTO);

  String authenticate(UserDTO userDTO);

  boolean validateToken(String token);
}
