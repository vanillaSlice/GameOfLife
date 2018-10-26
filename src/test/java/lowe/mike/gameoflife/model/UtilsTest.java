package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link Utils} tests.
 *
 * @author Mike Lowe
 */
public class UtilsTest {

  @Test
  public void requirePositiveNumber_withNegative_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Utils.requirePositiveNumber(-1, "number is -1"),
        "number is -1");
  }

  @Test
  public void requirePositiveNumber_withZero_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Utils.requirePositiveNumber(0, "number is 0"),
        "number is 0");
  }

  @Test
  public void requirePositiveNumber_withPositive_returnsNumber() {
    assertEquals(1, Utils.requirePositiveNumber(1, "number is 1"));
  }

}
