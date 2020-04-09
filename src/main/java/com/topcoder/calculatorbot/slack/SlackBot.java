package com.topcoder.calculatorbot.slack;

import com.topcoder.calculatorbot.exceptions.InvalidSumException;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.Controller;
import me.ramswaroop.jbot.core.slack.EventType;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@Profile("slack")
public class SlackBot extends Bot {

  private static final Logger logger = LoggerFactory.getLogger(SlackBot.class);

  /**
   * Slack token from application.properties file. You can get your slack token
   * next <a href="https://my.slack.com/services/new/bot">creating a new bot</a>.
   */
  @Value("${slackBotToken}")
  private String slackToken;

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
    logger.info("Equation received: " + event.getText());
    String text = event.getText();
    try {
      String result = calculator.calculate(text).toPlainString();
      reply(session, event, new Message(result));
    } catch (InvalidSumException e) {
      reply(session, event, new Message(e.getMessage()));
    }
  }



}