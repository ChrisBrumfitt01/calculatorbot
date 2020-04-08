package com.topcoder.calculatorbot.slack;

import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.common.JBot;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.WebSocketSession;

@JBot
@Profile("slack")
public class SlackBot extends Bot {

  private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

  /**
   * Slack token from application.properties file. You can get your slack token
   * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
   */
//  @Value("${slackBotToken}")
  private String slackToken = "xoxb-1047628658710-1039072154914-vivhQRfikKQJ2MbBKZWkWYTM";

  @Autowired
  private Calculator calculator;

  @Override
  public String getSlackToken() {
    return slackToken;
  }

  @Override
  public Bot getSlackBot() {
    return this;
  }


  @Controller(events = EventType.DIRECT_MESSAGE)
  public void onReceiveDM(WebSocketSession session, Event event) {
    String text = event.getText();
    String result = calculator.calculate(text).toPlainString();
    reply(session, event, result);
  }



}