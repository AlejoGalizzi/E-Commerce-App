package com.alejogalizzi.ecommerce.controller;

import com.alejogalizzi.ecommerce.model.dto.UserDTO;
import com.alejogalizzi.ecommerce.model.response.JwtResponse;
import com.alejogalizzi.ecommerce.service.abstraction.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @Autowired
  private IUserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserDTO user) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
  }

  @PostMapping(value = "/authenticate")
  public ResponseEntity<?> createAuthenticateToken(@RequestBody UserDTO user) {
    return ResponseEntity.ok(new JwtResponse(userService.authenticate(user)));
  }

  @PostMapping(value = "/validate-token")
  public ResponseEntity<?> validateToken(@RequestParam String token) {
    if(userService.validateToken(token)) {
      return ResponseEntity.ok().build();
    }else return ResponseEntity.notFound().build();
  }
}
