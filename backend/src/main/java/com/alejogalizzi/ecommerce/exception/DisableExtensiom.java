package com.alejogalizzi.ecommerce.exception;

import org.springframework.security.authentication.DisabledException;

public class DisableExtensiom extends DisabledException {

  public DisableExtensiom(String msg) {
    super(msg);
  }
}
