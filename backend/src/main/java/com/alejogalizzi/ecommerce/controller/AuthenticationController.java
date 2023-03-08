package com.alejogalizzi.ecommerce.controller;

import com.alejogalizzi.ecommerce.exception.DisableException;
import com.alejogalizzi.ecommerce.exception.InvalidCredentialsException;
import com.alejogalizzi.ecommerce.jwt.JwtTokenUtil;
import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import com.alejogalizzi.ecommerce.model.response.JwtResponse;
import com.alejogalizzi.ecommerce.service.JwtUserDetailsService;
import com.alejogalizzi.ecommerce.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @Autowired
  private IUserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  public AuthenticationController(JwtTokenUtil jwtTokenUtil) {
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<?> createAuthenticateToken(@RequestBody UserDTO user) {
    authenticate(user.getUsername(), user.getPassword());

    final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

    final String token = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  @PostMapping(value = "/validate-token")
  public ResponseEntity<?> validateToken(@RequestParam String token) {
    if(jwtTokenUtil.validateToken(token, jwtTokenUtil.getUsernameFromToken(token))) {
      return ResponseEntity.ok().build();
    }else return ResponseEntity.notFound().build();
  }

  private void authenticate(String username, String password) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new DisableException("User disabled");
    } catch (BadCredentialsException e) {
      throw new InvalidCredentialsException("Wrong credentials");
    }
  }
}
