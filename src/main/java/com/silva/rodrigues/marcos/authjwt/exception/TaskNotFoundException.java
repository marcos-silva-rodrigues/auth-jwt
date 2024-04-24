package com.silva.rodrigues.marcos.authjwt.exception;

public class TaskNotFoundException extends RuntimeException{

  public TaskNotFoundException(Long id) {
    super("not found task with id " + id);
  }
}
