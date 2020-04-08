package com.topcoder.calculatorbot.slack;

import com.topcoder.calculatorbot.exceptions.InvalidSumException;
import org.springframework.stereotype.Component;

@Component
public class CalculatorValidator {

  private static final String VALID_CHARS = ".0123456789+-*^/()";

  public void validate(String sum) {
    checkLastCharacter(sum);
    checkBracketValidity(sum);
    checkForInvalidCharacters(sum);
  }

  private void checkForInvalidCharacters(String sum) {
    for(char c : sum.toCharArray()) {
      if(VALID_CHARS.indexOf(c) == -1) {
        throw new InvalidSumException("Invalid sum: An invalid character was detected: " + c);
      }
    }
  }

  private void checkLastCharacter(String sum) {
    char lastChar = sum.charAt(sum.length()-1);
    if(!Character.isDigit(lastChar) && lastChar != ')') {
      throw new InvalidSumException("Invalid sum: Last character of the sum must be numeric or a closing bracket");
    }
  }

  private void checkBracketValidity(String sum) {
    int openBracketCount = 0;
    int closedBracketCount = 0;

    for(char c : sum.toCharArray()) {
      if(c == '(') {
        openBracketCount++;
      }
      if(c == ')') {
        closedBracketCount++;
      }
      if(closedBracketCount > openBracketCount) {
        throw new InvalidSumException("Invalid sum: There are closing brackets before an open bracket");
      }
    }

    if(openBracketCount != closedBracketCount) {
      throw new InvalidSumException(String.format("Invalid sum: There are %s open brackets and %s close brackets", openBracketCount, closedBracketCount));
    }
  }

}
