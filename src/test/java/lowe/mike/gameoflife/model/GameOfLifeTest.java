package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

/**
 * {@link GameOfLife} tests.
 *
 * @author Mike Lowe
 */
public class GameOfLifeTest {

  private final Grid grid = mock(Grid.class);
  private final GameOfLife gameOfLife = new GameOfLife(grid);

  @Test
  public void constructor_nullGrid_throwsNullPointerException() {
    assertThrows(NullPointerException.class,
        () -> new GameOfLife(null),
        "grid is null");
  }

  @Test
  public void next() {
    // execution and verification
    gameOfLife.next();
    assertEquals(1, gameOfLife.getGeneration());
    gameOfLife.next();
    assertEquals(2, gameOfLife.getGeneration());
    gameOfLife.next();
    assertEquals(3, gameOfLife.getGeneration());
    verify(grid, times(3)).nextGeneration();
  }

  @Test
  public void setSpeed_nullSpeed_throwsNullPointerException() {
    assertThrows(NullPointerException.class,
        () -> gameOfLife.setSpeed(null),
        "speed is null");
  }

  @Test
  public void clear() {
    // setup
    gameOfLife.play();

    // execution
    gameOfLife.clear();

    // verification
    assertEquals(0, gameOfLife.getGeneration());
    verify(grid).clear();
  }

  @Test
  public void reset() {
    // setup
    gameOfLife.play();

    // execution
    gameOfLife.reset();

    // verification
    assertEquals(0, gameOfLife.getGeneration());
    verify(grid, times(2)).randomGeneration(any());
  }

}
