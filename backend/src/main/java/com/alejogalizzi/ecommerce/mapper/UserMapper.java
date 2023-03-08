package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static UserDTO mapEntityToDto(User user) {
    return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
  }

}
