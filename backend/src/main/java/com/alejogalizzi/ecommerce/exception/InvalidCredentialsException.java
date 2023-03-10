package com.alejogalizzi.ecommerce.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidCredentialsException extends BadCredentialsException {

  public InvalidCredentialsException(String msg) {
    super(msg);
  }
}
