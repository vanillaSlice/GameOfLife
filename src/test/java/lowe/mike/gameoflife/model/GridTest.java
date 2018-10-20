package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link Grid} tests.
 *
 * @author Mike Lowe
 */
public class GridTest {

  private static final int NUMBER_OF_ROWS = 5;
  private static final int NUMBER_OF_COLUMNS = 5;

  private Grid grid;
  private boolean[][] expectedAlive;

  /**
   * Test setup.
   */
  @BeforeEach
  public void setUp() {
    grid = new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
    expectedAlive = new boolean[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
  }

  @Test
  public void constructor_negativeNumberOfRows_throwsNegativeArraySizeException() {
    assertThrows(NegativeArraySizeException.class,
        () -> new Grid(-1, NUMBER_OF_COLUMNS));
  }

  @Test
  public void constructor_negativeNumberOfColumns_throwsNegativeArraySizeException() {
    assertThrows(NegativeArraySizeException.class,
        () -> new Grid(NUMBER_OF_ROWS, -1));
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
