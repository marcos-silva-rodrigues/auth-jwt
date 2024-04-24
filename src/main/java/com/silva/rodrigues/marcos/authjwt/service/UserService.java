package com.silva.rodrigues.marcos.authjwt.service;

import com.silva.rodrigues.marcos.authjwt.dto.CreateUserDto;
import com.silva.rodrigues.marcos.authjwt.exception.UsernameAlreadyExistsException;
import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.model.UserDetailsImpl;
import com.silva.rodrigues.marcos.authjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public User create(CreateUserDto dto) {
    var alreadyExists = userRepository.existsByUsername(dto.username());
    if (alreadyExists) {
      throw new UsernameAlreadyExistsException(dto.username());
    }
    var newUser = new User();
    newUser.setUsername(dto.username());
    newUser.setPassword(passwordEncoder.encode(dto.password()));
    newUser.setBio(dto.bio());

    return userRepository.save(newUser);
  }

  @Override
  public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    return new UserDetailsImpl(user);
  }
}
