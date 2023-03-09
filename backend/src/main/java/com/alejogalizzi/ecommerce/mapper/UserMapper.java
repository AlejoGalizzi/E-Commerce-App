package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.model.authorization.Role;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import com.alejogalizzi.ecommerce.util.constants.Roles;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static UserDTO mapEntityToDto(User user) {
    return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), getEnumRoles(user.getRoles()));
  }

  private static Set<Roles> getEnumRoles(Set<Role> roles) {
    return roles.stream().map(role -> Roles.valueOf(role.getName())).collect(Collectors.toSet());
  }
}
