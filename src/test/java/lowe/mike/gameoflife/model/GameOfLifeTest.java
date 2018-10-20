package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link GameOfLife} tests.
 *
 * @author Mike Lowe
 */
public class GameOfLifeTest {

  private GameOfLife gameOfLife;

  /**
   * Test setup.
   */
  @BeforeEach
  public void setUp() {
    gameOfLife = new GameOfLife(3, 3);
  }

  @Test
  public void constructor_negativeNumberOfRows_throwsNegativeArraySizeException() {
    assertThrows(NegativeArraySizeException.class,
        () -> new GameOfLife(-1, 3));
  }

  @Test
  public void constructor_negativeNumberOfColumns_throwsNegativeArraySizeException() {
    assertThrows(NegativeArraySizeException.class,
        () -> new GameOfLife(3, -1));
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
  }

  @Test
  public void clear() {
    // setup
    gameOfLife.play();

    // execution
    gameOfLife.clear();

    // verification
    assertEquals(0, gameOfLife.getGeneration());
  }

}
