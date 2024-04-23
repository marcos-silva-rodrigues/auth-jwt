package com.silva.rodrigues.marcos.authjwt.service;

import com.silva.rodrigues.marcos.authjwt.dto.LoginDto;
import com.silva.rodrigues.marcos.authjwt.dto.TokenDto;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private AuthenticationManager manager;
  private JwtService jwtService;

  public TokenDto login(LoginDto loginDto) {
    var authenticationToken = new UsernamePasswordAuthenticationToken(
            loginDto.username(), loginDto.password());
    var authentication = manager.authenticate(authenticationToken);

    var user = (UserDetailsImpl) authentication.getPrincipal();
    return jwtService.generateToken(user.getUsername());
  }
}
