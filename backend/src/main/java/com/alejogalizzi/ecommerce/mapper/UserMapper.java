package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.jwt.JwtTokenUtil;
import com.alejogalizzi.ecommerce.model.authorization.Authority;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

  public static UserDTO mapEntityToDto(User user) {
    return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), convertTo(user.getAuthorities()));
  }

  private static List<String> convertTo(Set<Authority> authorities) {
    return authorities.stream()
        .map(Authority::getAuthority)
        .collect(Collectors.toList());
  }
}
