package com.silva.rodrigues.marcos.authjwt.exception;

public class InvalidJwtException extends RuntimeException {

  public InvalidJwtException() {
    super("Token JWT inválido ou expirado");
  }

}
