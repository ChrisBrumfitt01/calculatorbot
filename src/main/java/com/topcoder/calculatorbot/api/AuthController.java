package com.topcoder.calculatorbot.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @GetMapping("/auth")
  public ResponseEntity<String> authorise() {
    return ResponseEntity.ok("I love you Stef");
  }


}