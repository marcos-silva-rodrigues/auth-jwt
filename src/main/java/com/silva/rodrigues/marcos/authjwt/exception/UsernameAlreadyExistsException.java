package com.silva.rodrigues.marcos.authjwt.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

  public UsernameAlreadyExistsException(String username) {
    super("username " + username + " already in use");
  }

}
