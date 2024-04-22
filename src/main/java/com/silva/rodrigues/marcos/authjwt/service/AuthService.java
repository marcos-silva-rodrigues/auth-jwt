package com.silva.rodrigues.marcos.authjwt.service;

import com.silva.rodrigues.marcos.authjwt.dto.LoginDto;
import com.silva.rodrigues.marcos.authjwt.dto.TokenDto;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    var user = (User) authentication.getPrincipal();
    return jwtService.generateToken(user);
  }
}
