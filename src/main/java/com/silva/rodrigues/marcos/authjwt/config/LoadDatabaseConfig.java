package com.silva.rodrigues.marcos.authjwt.config;

import com.silva.rodrigues.marcos.authjwt.model.User;
import com.silva.rodrigues.marcos.authjwt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Profile("!prod")
public class LoadDatabaseConfig {

  private static Logger log = LoggerFactory.getLogger(LoadDatabaseConfig.class);

  @Bean
  public CommandLineRunner loadDatabase(UserRepository userRepository, PasswordEncoder encoder) {
    return args -> {
      log.warn("init database seed if not have users");
      var usersLength = userRepository.count();

      if (usersLength > 0) {
        log.info("have users in databases");
      } else {
        var user1 = new User();
        user1.setUsername("Fulano Silva");
        user1.setPassword(encoder.encode("123abcteste"));
        user1.setBio("bla bkla bkla bkla");

        var user2 = new User();
        user2.setUsername("Fulano Junior");
        user2.setPassword(encoder.encode("123abcteste"));
        user2.setBio("bla bkla bkla bkla");

        log.info("create user:"  + userRepository.save(user1));
        log.info("create user:"  + userRepository.save(user2));
      }
    };
  }
}
