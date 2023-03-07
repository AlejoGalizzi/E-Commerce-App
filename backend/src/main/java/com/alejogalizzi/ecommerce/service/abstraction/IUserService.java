package com.alejogalizzi.ecommerce.service.abstraction;

import com.alejogalizzi.ecommerce.model.dto.UserDTO;

public interface IUserService {

  UserDTO createUser(UserDTO userDTO);
}
