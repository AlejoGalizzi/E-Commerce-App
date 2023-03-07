package com.alejogalizzi.ecommerce.exception;

public class AlreadyRegister extends RuntimeException {

  public AlreadyRegister(String msg) {
    super(msg);
  }
}
