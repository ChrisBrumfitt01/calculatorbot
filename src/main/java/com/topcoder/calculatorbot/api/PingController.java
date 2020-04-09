package com.topcoder.calculatorbot.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  /**
   * If an application on Heroku is not interacted with for 30mins,
   * then it will go to sleep.
   * This endpoint exists so it can be pinged every so often, to keep the app up
   * @return
   */
  @GetMapping("/ping")
  public ResponseEntity ping() {
    return ResponseEntity.ok().build();
  }
}
