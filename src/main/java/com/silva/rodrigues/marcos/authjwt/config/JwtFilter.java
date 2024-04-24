package com.silva.rodrigues.marcos.authjwt.config;

import com.silva.rodrigues.marcos.authjwt.exception.InvalidJwtException;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import com.silva.rodrigues.marcos.authjwt.repository.UserRepository;
import com.silva.rodrigues.marcos.authjwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private UserRepository repository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, InvalidJwtException {
    var tokenJWT = getToken(request);
    if (tokenJWT == null) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String username = jwtService.getSubject(tokenJWT);
      var user = repository.findByUsername(username);

      if(user.isPresent()) {

        var principal = new UserDetailsImpl(user.get());
        var authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (InvalidJwtException e) {
      // ignore exception
    } finally {
      filterChain.doFilter(request, response);
    }
  }

  private String getToken(HttpServletRequest request) {
    var authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader != null) {
      return authorizationHeader.replace("Bearer ", "");
    }

    return null;
  }
}

