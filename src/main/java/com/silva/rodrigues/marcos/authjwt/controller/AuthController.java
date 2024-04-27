package com.silva.rodrigues.marcos.authjwt.controller;

import com.silva.rodrigues.marcos.authjwt.dto.CreateUserDto;
import com.silva.rodrigues.marcos.authjwt.dto.LoginDto;
import com.silva.rodrigues.marcos.authjwt.dto.TokenDto;
import com.silva.rodrigues.marcos.authjwt.dto.UserDto;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface AuthController {

  @Operation(summary = "Get JWT Token")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Token JWT to use in Bearer Header with timestamp to expiration",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "403", description = "Invalid credentials")
  })
  public TokenDto login(LoginDto loginDto);


  @Operation(summary = "Create new user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201",
                  description = "new user in system",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
          ),
          @ApiResponse(responseCode = "400", description = "Username already in use")
  })
  public ResponseEntity<UserDto> create(CreateUserDto createUserDto);


  @Operation(summary = "Get current user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "current user data",
                  content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
          ),
          @ApiResponse(responseCode = "403", description = "Not found current user")
  })
  public UserDto me(UserDetailsImpl principal);
}
