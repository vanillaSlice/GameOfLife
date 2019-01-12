package lowe.mike.gameoflife.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link Controller} unit tests.
 *
 * @author Mike Lowe
 */
public class ControllerTest {

  @Test
  public void setGameOfLife_nullGameOfLife_throwsNullPointerException() {
    NullPointerException exception = assertThrows(NullPointerException.class,
        () -> new Controller().setGameOfLife(null));
    assertEquals("game of life is null", exception.getMessage());
  }
}
