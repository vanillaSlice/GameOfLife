package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link Utils} unit tests.
 *
 * @author Mike Lowe
 */
public class UtilsTest {

  @Test
  public void requirePositiveNumber_withNegative_throwsIllegalArgumentException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> Utils.requirePositiveNumber(-1, "number is -1"));
    assertEquals("number is -1", exception.getMessage());
  }

  @Test
  public void requirePositiveNumber_withZero_throwsIllegalArgumentException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> Utils.requirePositiveNumber(0, "number is 0"));
    assertEquals("number is 0", exception.getMessage());
  }

  @Test
  public void requirePositiveNumber_withPositive_returnsNumber() {
    assertEquals(1, Utils.requirePositiveNumber(1, "number is 1"));
  }
}
