package lowe.mike.gameoflife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * {@link GameOfLifeApplication} unit tests.
 *
 * @author Mike Lowe
 */
public class GameOfLifeApplicationTest {

  @Test
  public void constructor_nullGameOfLife_throwsNullPointerException() {
    NullPointerException exception = assertThrows(NullPointerException.class,
        () -> new GameOfLifeApplication(null));
    assertEquals("game of life is null", exception.getMessage());
  }
}
