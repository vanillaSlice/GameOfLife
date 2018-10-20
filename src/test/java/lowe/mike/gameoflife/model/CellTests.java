package lowe.mike.gameoflife.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Cell tests.
 *
 * @author Mike Lowe
 */
public final class CellTests {

  private static final int NUMBER_OF_NEIGHBOURS = 4;
  private Cell cell;
  private Cell[] neighbours;

  /**
   * Test setup.
   */
  @Before
  public void setUp() {
    cell = new Cell();
    neighbours = new Cell[NUMBER_OF_NEIGHBOURS];
    for (int i = 0; i < NUMBER_OF_NEIGHBOURS; i++) {
      neighbours[i] = new Cell();
    }
    cell.setNeighbours(neighbours);
  }

  @Test
  public void test_calculateNextState_underPopulation() {
    test_calculateNextState(true, 1, false);
  }

  private void test_calculateNextState(boolean isAlive, int numberOfAliveNeighbours,
      boolean expectedAlive) {
    // setup
    cell.setAlive(isAlive);
    setNumberOfAliveNeighbours(numberOfAliveNeighbours);

    // execution
    cell.calculateNextState();
    cell.goToNextState();

    // verification
    assertEquals(expectedAlive, cell.isAlive());
  }

  private void setNumberOfAliveNeighbours(int number) {
    for (int i = 0; i < number; i++) {
      neighbours[i].setAlive(true);
    }
  }

  @Test
  public void test_calculateNextState_liveToNextGenerationWithTwo() {
    test_calculateNextState(true, 2, true);
  }

  @Test
  public void test_calculateNextState_liveToNextGenerationWithThree() {
    test_calculateNextState(true, 3, true);
  }

  @Test
  public void test_calculateNextState_overpopulation() {
    test_calculateNextState(true, 4, false);
  }

  @Test
  public void test_calculateNextState_reproduction() {
    test_calculateNextState(false, 3, true);
  }

  @Test
  public void test_calculateNextState_noReproductionWithTwo() {
    test_calculateNextState(false, 2, false);
  }

  @Test
  public void test_calculateNextState_noReproductionWithFour() {
    test_calculateNextState(false, 4, false);
  }

}
