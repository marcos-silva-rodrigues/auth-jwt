package com.silva.rodrigues.marcos.authjwt.dto;

import com.silva.rodrigues.marcos.authjwt.model.User;
import jakarta.persistence.Column;

public record UserDto(
        String id,
        String username,
        String bio
) {

  public UserDto(User user) {
    this(user.getId().toString(), user.getUsername(), user.getBio());
  }
}
