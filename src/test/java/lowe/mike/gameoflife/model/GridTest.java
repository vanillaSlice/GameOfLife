package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * {@link Grid} tests.
 *
 * @author Mike Lowe
 */
public class GridTest {

  private static final int NUMBER_OF_ROWS = 5;
  private static final int NUMBER_OF_COLUMNS = 5;

  private final Grid grid = new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
  private final boolean[][] expectedAlive = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];

  @Test
  public void constructor_negativeNumberOfRows_throwsIllegalArgumentException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Grid(-1, NUMBER_OF_COLUMNS));
    assertEquals("number of rows is -1", exception.getMessage());
  }

  @Test
  public void constructor_negativeNumberOfColumns_throwsIllegalArgumentException() {
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Grid(NUMBER_OF_ROWS, -1));
    assertEquals("number of columns is -1", exception.getMessage());
  }

  @Test
  public void nextGeneration_blank() {
    // initial verification
    verifyGrid();

    // execution
    grid.nextGeneration();

    // verification
    verifyGrid();
  }

  @Test
  public void nextGeneration_single() {
    // setup
    grid.getCell(0, 0).setAlive(true);
    expectedAlive[0][0] = true;

    // initial verification
    verifyGrid();

    // execution
    grid.nextGeneration();

    // verification
    expectedAlive[0][0] = false;
    verifyGrid();
  }

  @Test
  public void nextGeneration_stillLife() {
    // setup
    grid.getCell(1, 1).setAlive(true);
    grid.getCell(1, 2).setAlive(true);
    grid.getCell(2, 1).setAlive(true);
    grid.getCell(2, 2).setAlive(true);
    expectedAlive[1][1] = true;
    expectedAlive[1][2] = true;
    expectedAlive[2][1] = true;
    expectedAlive[2][2] = true;

    // initial verification
    verifyGrid();

    // execution
    grid.nextGeneration();

    // verification
    verifyGrid();
  }

  @Test
  public void nextGeneration_oscillator() {
    // setup
    grid.getCell(1, 2).setAlive(true);
    grid.getCell(2, 2).setAlive(true);
    grid.getCell(3, 2).setAlive(true);
    expectedAlive[1][2] = true;
    expectedAlive[2][2] = true;
    expectedAlive[3][2] = true;

    // initial verification
    verifyGrid();

    // execution
    grid.nextGeneration();

    // verification
    expectedAlive[1][2] = false;
    expectedAlive[2][1] = true;
    expectedAlive[2][3] = true;
    expectedAlive[3][2] = false;
    verifyGrid();
  }

  @Test
  public void nextGeneration_glider() {
    // setup
    grid.getCell(1, 2).setAlive(true);
    grid.getCell(2, 3).setAlive(true);
    grid.getCell(3, 1).setAlive(true);
    grid.getCell(3, 2).setAlive(true);
    grid.getCell(3, 3).setAlive(true);
    expectedAlive[1][2] = true;
    expectedAlive[2][3] = true;
    expectedAlive[3][1] = true;
    expectedAlive[3][2] = true;
    expectedAlive[3][3] = true;

    // initial verification
    verifyGrid();

    // execution
    grid.nextGeneration();

    // verification
    expectedAlive[1][2] = false;
    expectedAlive[2][1] = true;
    expectedAlive[3][1] = false;
    expectedAlive[4][2] = true;
    verifyGrid();
  }

  @Test
  public void clear_allCellsDead() {
    // setup
    grid.getCell(0, 0).setAlive(true);
    expectedAlive[0][0] = true;

    // initial verification
    verifyGrid();

    // execution
    grid.clear();

    // verification
    expectedAlive[0][0] = false;
    verifyGrid();
  }

  @Test
  public void randomGeneration_randomlySetsCellsAsAliveOrDead() {
    // setup
    Random random = mock(Random.class);
    when(random.nextBoolean())
        .thenReturn(true)
        .thenReturn(false)
        .thenReturn(true)
        .thenReturn(false);

    // execution
    grid.randomGeneration(random);

    // verification
    expectedAlive[0][0] = true;
    expectedAlive[0][2] = true;
    verifyGrid();
  }

  private void verifyGrid() {
    for (int rowIndex = 0; rowIndex < NUMBER_OF_ROWS; rowIndex++) {
      for (int columnIndex = 0; columnIndex < NUMBER_OF_COLUMNS; columnIndex++) {
        Cell cell = grid.getCell(rowIndex, columnIndex);
        boolean isAlive = expectedAlive[rowIndex][columnIndex];
        assertEquals(isAlive, cell.isAlive());
      }
    }
  }

}
