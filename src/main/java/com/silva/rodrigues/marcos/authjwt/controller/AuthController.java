package com.silva.rodrigues.marcos.authjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @PostMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/create")
  public String create() {
    return "create";
  }

  @GetMapping("/me")
  public String me() {
    return "me";
  }
}
