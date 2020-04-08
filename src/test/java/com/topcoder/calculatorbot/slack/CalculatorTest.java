package com.topcoder.calculatorbot.slack;

import static org.assertj.core.api.Assertions.assertThat;


import com.topcoder.calculatorbot.exceptions.InvalidSumException;
import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTest {

  @Autowired
  private Calculator calculator;

  @Test
  public void shouldHandleSingleDigitMultiplication() {
    assertThat(calculator.calculate("2*2")).isEqualTo(toBigDecimal(4));
  }

  @Test
  public void shouldHandleMultiDigitMultiplication() {
    assertThat(calculator.calculate("12*2")).isEqualTo(toBigDecimal(24));
  }

  @Test
  public void shouldHandleBothMultiDigitMultiplication() {
    assertThat(calculator.calculate("12*10").toPlainString()).isEqualTo("120");
  }

  @Test
  public void shouldHandleSpaces() {
    assertThat(calculator.calculate("12 * 10").toPlainString()).isEqualTo("120");
  }

  @Test
  public void shouldHandleSingleDigitDivision () {
    assertThat(calculator.calculate("6/3")).isEqualTo(toBigDecimal(2));
  }

  @Test
  public void shouldHandleMultiDigitDivision () {
    assertThat(calculator.calculate("24/6")).isEqualTo(toBigDecimal(4));
  }

  @Test
  public void shouldHandleSingleDigitAddition() {
    assertThat(calculator.calculate("2+5")).isEqualTo(toBigDecimal(7));
  }

  @Test
  public void shouldHandleMultiDigitAddition() {
    assertThat(calculator.calculate("52 + 25")).isEqualTo(toBigDecimal(77));
  }

  @Test
  public void shouldHandleSingleDigitSubtraction() {
    assertThat(calculator.calculate("8-3")).isEqualTo(toBigDecimal(5));
  }

  @Test
  public void shouldHandleMultiDigitSubtraction() {
    assertThat(calculator.calculate("52 - 10")).isEqualTo(toBigDecimal(42));
  }

  @Test
  public void shouldPerformAdditionAndMultiplicationInCorrectOrder() {
    assertThat(calculator.calculate("2 + 5 * 6")).isEqualTo(toBigDecimal(32));
  }

  @Test
  public void shouldHandleSumWhenItSaysPlusANegativeNumber() {
    assertThat(calculator.calculate("12 + -5")).isEqualTo(toBigDecimal(7));
  }

  @Test(expected = InvalidSumException.class)
  public void shouldThrowException_whenThereIsAnUnequalNumberOfOpeningAndClosingBrackets() {
    calculator.calculate("((2 + 1)");
  }

  @Test(expected = InvalidSumException.class)
  public void shouldThrowException_whenThereIsAClosingBracket_beforeAnOpeningBRacketForIt() {
    calculator.calculate(")2 + 1(");
  }

  @Test
  public void shouldHandleBracketsInBasicSum() {
    assertThat(calculator.calculate("(2 + 3)")).isEqualTo(toBigDecimal(5));
  }

  @Test
  public void shouldHandleBracketsInAnotherBasicSum() {
    assertThat(calculator.calculate("(2 + 3) * (7 - 3)").toPlainString()).isEqualTo(toBigDecimal(20).toPlainString());
  }

  @Test
  public void shouldHandleMultipleSetsOfBrackets() {
    assertThat(calculator.calculate("(2 * (4+2))")).isEqualTo(toBigDecimal(12));
  }

  @Test
  public void shouldHandleMultipleSetsOfBrackets_alternateSum() {
    assertThat(calculator.calculate("((8*2))")).isEqualTo(toBigDecimal(16));
  }

  @Test
  public void shouldHandleThreeNumberSum() {
    assertThat(calculator.calculate("7 - 2 + 8")).isEqualTo(toBigDecimal(13));
  }

  @Test
  public void shouldHandleNegativeNumberAtTheStart() {
    assertThat(calculator.calculate("-2 + 8")).isEqualTo(toBigDecimal(6));
  }

  @Test
  public void shouldHandleSumWithNegativesAndBrackets() {
    assertThat(calculator.calculate("2 * (8+3)")).isEqualTo(toBigDecimal(22));
  }

  @Test
  public void shouldHandleSumThatBeginsWithZero() {
    assertThat(calculator.calculate("0 - 5 + 8")).isEqualTo(toBigDecimal(3));
  }

  @Test
  public void shouldHandleSumThatBeginsWithZeroAndIsALongSum() {
    assertThat(calculator.calculate("0 - 5 + 8 - 6 * 10 + 7").toPlainString()).isEqualTo("-50");
  }

  @Test
  public void shouldHandleSumWithDecimals() {
    assertThat(calculator.calculate("2.5 + 1.2")).isEqualTo(BigDecimal.valueOf(3.7));
  }

  @Test
  public void shouldHandleSumWithMorePreciseDecimals() {
    assertThat(calculator.calculate("2.58654 + 1.296")).isEqualTo(BigDecimal.valueOf(3.88254));
  }

  @Test
  public void shouldHandleSumThatBeginsWithZeroAndIsALongSumAndHasDecimals() {
    assertThat(calculator.calculate("0.41 - 5.9251 + 8.4245 - 6.543 * 10 + 7.571")).isEqualTo(BigDecimal.valueOf(-54.9496));
  }

  @Test
  public void shouldHandleNumberWithDecimalPointAtTheStart() {
    assertThat(calculator.calculate("2 + .642 + 1")).isEqualTo(BigDecimal.valueOf(3.642));
  }

  @Test
  public void shouldHandleDecimalPointAtTheEnd() {
    assertThat(calculator.calculate("2 + 8. + 1")).isEqualTo(BigDecimal.valueOf(11));
  }

  @Test(expected = InvalidSumException.class)
  public void shouldThrowException_whenNumberIsInvalidWithMultipleDecimalPoints() {
    assertThat(calculator.calculate("2 + 8.5324.234 + 1")).isEqualTo(BigDecimal.valueOf(11));
  }

  @Test
  public void shouldHandleDivisionOfDecimalNumbers_ThatResultsInWholeNumberAnswer() {
    assertThat(calculator.calculate("10 / 2.5")).isEqualTo(BigDecimal.valueOf(4));
  }

  @Test
  public void shouldHandleDivisionOfWholeNumbers_ThatResultsInDecimalAnswer() {
    assertThat(calculator.calculate("10 / 4")).isEqualTo(BigDecimal.valueOf(2.5));
  }

  @Test
  public void shouldHandleDivision_involvingRecurringInfiniteDigits() {
    BigDecimal expected = new BigDecimal("3.333333333333333333333333333333333", MathContext.DECIMAL128);
    assertThat(calculator.calculate("10 / 3")).isEqualTo(expected);
  }

  @Test
  public void shouldHandleBasicPower() {
    assertThat(calculator.calculate("2 ^ 2").toPlainString()).isEqualTo("4");
  }

  @Test
  public void shouldHandleBasicPower_Alternate() {
    assertThat(calculator.calculate("2 ^ 3").toPlainString()).isEqualTo("8");
  }

  @Test
  public void shouldHandlePowers_ToADecimalPlace() {
    assertThat(calculator.calculate("2 ^ 2.5").toPlainString()).isEqualTo("5.656854249492381");
  }

  @Test
  public void shouldAdhereToBodmas_whenPowersAreIncluded() {
    assertThat(calculator.calculate("0.41 - 5.9251 ^ 2 + 8.4245 - 6.543 * 10 + 7.571")).isEqualTo(BigDecimal.valueOf(-84.13131001));
  }

  private BigDecimal toBigDecimal(int i) {
    return new BigDecimal(i);
  }
}
