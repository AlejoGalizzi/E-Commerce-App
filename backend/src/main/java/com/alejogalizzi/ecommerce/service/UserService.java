package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.exception.AlreadyRegister;
import com.alejogalizzi.ecommerce.mapper.UserMapper;
import com.alejogalizzi.ecommerce.model.authorization.Role;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import com.alejogalizzi.ecommerce.service.abstraction.IUserService;
import com.alejogalizzi.ecommerce.util.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Override
  public UserDTO createUser(UserDTO userDTO) {
    if (userRepository.findByUsername(userDTO.getUsername()) != null) {
      throw new AlreadyRegister("User is already registered");
    }
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
    user.setRole(Role.ROLE_USER);
    return UserMapper.mapEntityToDto(userRepository.save(user));
  }
}
