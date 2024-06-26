package com.silva.rodrigues.marcos.authjwt.controller;

import com.silva.rodrigues.marcos.authjwt.dto.CreateUserDto;
import com.silva.rodrigues.marcos.authjwt.dto.LoginDto;
import com.silva.rodrigues.marcos.authjwt.dto.TokenDto;
import com.silva.rodrigues.marcos.authjwt.dto.UserDto;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import com.silva.rodrigues.marcos.authjwt.service.AuthService;
import com.silva.rodrigues.marcos.authjwt.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

  private UserService userService;
  private AuthService authService;

  @PostMapping("/login")
  public TokenDto login(@Valid @RequestBody LoginDto loginDto) {

    return authService.login(loginDto);
  }

  @PostMapping("/create")
  public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserDto createUserDto) {
    User user = userService.create(createUserDto);
    return ResponseEntity.of(Optional.of(new UserDto(user)));
  }

  @GetMapping("/me")
  public UserDto me(@AuthenticationPrincipal UserDetailsImpl principal) {
    return new UserDto(principal.getUser());
  }
}
