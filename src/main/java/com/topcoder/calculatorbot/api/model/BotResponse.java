package com.topcoder.calculatorbot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BotResponse {

  @JsonProperty("bot_user_id")
  private String botUserId;

  @JsonProperty("bot_access_token")
  private String botAccessToken;


  public String getBotUserId() {
    return botUserId;
  }

  public String getBotAccessToken() {
    return botAccessToken;
  }
}
