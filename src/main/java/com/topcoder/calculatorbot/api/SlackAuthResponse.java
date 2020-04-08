package com.topcoder.calculatorbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackAuthResponse {

  @JsonProperty("access_token")
  private String accessToken;

  public String getAccessToken() {
    return accessToken;
  }
}
