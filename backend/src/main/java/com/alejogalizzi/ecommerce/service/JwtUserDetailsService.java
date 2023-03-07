package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.exception.NotFoundException;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.authorization.Authority;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private IUserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new NotFoundException("User not found with username" + username);
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(Authority.USER_ROLE);

    if (user.isAdmin()) {
      authorities.add(Authority.ADMIN_ROLE);
    }

    user.setAuthorities(authorities.stream().map(authority -> (Authority) authority).collect(
        Collectors.toSet()));
    return user;
  }
}
