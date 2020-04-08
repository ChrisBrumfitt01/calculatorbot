package com.topcoder.calculatorbot.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SlackAuthResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("bot_user_id")
  private String botUserId;

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getBotUserId() {
    return botUserId;
  }
}
