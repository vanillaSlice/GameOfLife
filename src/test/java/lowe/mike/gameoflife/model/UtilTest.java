package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link Util} tests.
 *
 * @author Mike Lowe
 */
public class UtilTest {

  @Test
  public void requirePositiveNumber_withNegative_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Util.requirePositiveNumber(-1, "number is -1"),
        "number is -1");
  }

  @Test
  public void requirePositiveNumber_withZero_throwsIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class,
        () -> Util.requirePositiveNumber(0, "number is 0"),
        "number is 0");
  }

  @Test
  public void requirePositiveNumber_withPositive_returnsNumber() {
    assertEquals(1, Util.requirePositiveNumber(1, "number is 1"));
  }

}
