package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.exception.NotFoundException;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.authorization.Authority;
import com.alejogalizzi.ecommerce.repository.IAuthorityRepository;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IAuthorityRepository authorityRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new NotFoundException("User not found with username" + username);
    }

    List<Authority> authorities = authorityRepository.findAll();
    user.setAuthorities(authorities.stream().filter(authority -> authority.getUser() == user)
        .collect(Collectors.toSet()));
    return user;
  }
}
