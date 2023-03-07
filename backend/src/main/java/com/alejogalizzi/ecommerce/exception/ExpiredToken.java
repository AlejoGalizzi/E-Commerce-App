package com.alejogalizzi.ecommerce.exception;

public class ExpiredToken extends RuntimeException {

  public ExpiredToken(String msg) {
    super(msg);
  }
}
