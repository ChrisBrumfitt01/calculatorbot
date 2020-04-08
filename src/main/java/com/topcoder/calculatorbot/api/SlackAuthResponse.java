package com.topcoder.calculatorbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topcoder.calculatorbot.api.model.BotResponse;

public class SlackAuthResponse {

  @JsonProperty("access_token")
  private String accessToken;

  private BotResponse bot;

  public String getAccessToken() {
    return accessToken;
  }
}
