package com.topcoder.calculatorbot.slack;

import com.topcoder.calculatorbot.exceptions.InvalidSumException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

  @Autowired
  private CalculatorValidator validator;

  public BigDecimal calculate(String sum) {
    sum = sum.replaceAll(" ", "");
    sum = sum.replaceAll("\\+-", "-");
    validator.validate(sum);

    while(hasBrackets(sum)) {
      int x = sum.lastIndexOf('(');
      String part = sum.substring(x+1);
      int closing = part.indexOf(')');
      String innerSum = part.substring(0, closing);
      BigDecimal innerSumResult = calculateSumWithoutBrackets(innerSum);
      sum = sum.replace("(" + innerSum + ")", innerSumResult.toString());
    }

    return calculateSumWithoutBrackets(sum).stripTrailingZeros();
  }

  private BigDecimal calculateSumWithoutBrackets(String sum) {
    while(hasPowers(sum)) {
      sum = doPowers(sum);
    }

    while(hasMultiplicationOrDivision(sum)) {
      sum = doMultiplicationAndDivision(sum);
    }

    while (hasAdditionOrSubtraction(sum)) {
      sum = doAdditionAndSubtraction(sum);
    }

    return new BigDecimal(sum);
  }

  private boolean hasBrackets(String sum) {
    return sum.contains("(");
  }

  private boolean hasMultiplicationOrDivision(String sum) {
    return sum.contains("*") || sum.contains("/");
  }

  private boolean hasAdditionOrSubtraction(String sum) {
    return sum.contains("+") || sum.lastIndexOf("-") > 0;
  }

  private boolean hasPowers(String sum) {
    return sum.contains("^");
  }

  private String calculatePartOfSum(String sum, List<Character> signs) {
    int index = -1;
    char sign = 0;
    for(int i=1; i<sum.length(); i++) {
      if(signs.contains(sum.charAt(i))) {
        index = i;
        sign = sum.charAt(i);
        break;
      }
    }

    String beforeNum = "";
    String afterNum = "";

    int i = index-1;
    while (i >= 0 &&
        (  Character.isDigit(sum.charAt(i)) || sum.charAt(i)=='.'   ||    ( sum.charAt(i)=='-' && i==0  )     ) ){
      beforeNum = sum.charAt(i) + beforeNum;
      i--;
    }


    int j = index+1;
    while(j < sum.length() && ( Character.isDigit(sum.charAt(j)) || sum.charAt(j)=='.' )     ) {
      afterNum = afterNum + sum.charAt(j);
      j++;
    }

    validateNumberInSum(beforeNum);
    validateNumberInSum(afterNum);

    String innerSum = beforeNum + sign + afterNum;
    BigDecimal result = null;
    switch (sign) {
      case '*':
        result = new BigDecimal(beforeNum).multiply(new BigDecimal(afterNum));
        break;

      case '/':
        result = new BigDecimal(beforeNum).divide(new BigDecimal(afterNum), MathContext.DECIMAL128);
        break;

      case '+':
        result = new BigDecimal(beforeNum).add(new BigDecimal(afterNum));
        break;

      case '-':
        result = new BigDecimal(beforeNum).subtract(new BigDecimal(afterNum));
        break;

      case '^':
        result = BigDecimal.valueOf(Math.pow(Double.parseDouble(beforeNum), Double.parseDouble(afterNum)));
        break;

      default:
        break;
    }
    return sum.replace(innerSum, result.toPlainString());
  }

  private String doPowers(String sum) {
    return calculatePartOfSum(sum, newList('^'));
  }

  private String doMultiplicationAndDivision(String sum) {
    return calculatePartOfSum(sum, newList('*', '/'));
  }

  private String doAdditionAndSubtraction(String sum) {
    return calculatePartOfSum(sum, newList('+', '-'));
  }

  private void validateNumberInSum(String number) {
    int countOfDots = 0;
    for(char c : number.toCharArray()) {
      if(c=='.')
        countOfDots++;
    }

    if(countOfDots > 1) {
      throw new InvalidSumException("Invalid number found in sum: " + number);
    }
  }

  private List<Character> newList(Character... elements) {
    List<Character> list = new ArrayList<>();
    list.addAll(Arrays.asList(elements));
    return list;
  }
}
