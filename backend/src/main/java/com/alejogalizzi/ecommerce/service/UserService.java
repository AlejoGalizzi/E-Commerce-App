package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.exception.AlreadyRegister;
import com.alejogalizzi.ecommerce.exception.DisableException;
import com.alejogalizzi.ecommerce.exception.InvalidCredentialsException;
import com.alejogalizzi.ecommerce.jwt.JwtTokenUtil;
import com.alejogalizzi.ecommerce.mapper.UserMapper;
import com.alejogalizzi.ecommerce.model.authorization.Role;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import com.alejogalizzi.ecommerce.repository.IRoleRepository;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import com.alejogalizzi.ecommerce.service.abstraction.IUserService;
import com.alejogalizzi.ecommerce.util.constants.Roles;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IRoleRepository roleRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public UserDTO register(UserDTO userDTO) {
    if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
      throw new AlreadyRegister("User is already registered");
    }
    Role role = roleRepository.findByName(Roles.ROLE_USER.name());
    User user = User.builder().username(userDTO.getUsername()).roles(Set.of(role))
        .password(passwordEncoder.encode(userDTO.getPassword())).build();
    return UserMapper.mapEntityToDto(userRepository.save(user));
  }

  @Override
  public String authenticate(UserDTO userDTO) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
    } catch (DisabledException e) {
      throw new DisableException("User disabled");
    } catch (BadCredentialsException e) {
      throw new InvalidCredentialsException("Wrong credentials");
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUsername());

    return jwtTokenUtil.generateToken(userDetails);
  }

  @Override
  public boolean validateToken(String token) {
    return jwtTokenUtil.validateToken(token, jwtTokenUtil.getUsernameFromToken(token));
  }
}
