package com.alejogalizzi.ecommerce.exception;

import org.springframework.security.authentication.DisabledException;

public class DisableException extends DisabledException {

  public DisableException(String msg) {
    super(msg);
  }
}
