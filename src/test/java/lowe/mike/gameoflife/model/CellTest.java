package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link Cell} tests.
 *
 * @author Mike Lowe
 */
public class CellTest {

  private static final int NUMBER_OF_NEIGHBOURS = 4;

  private Cell cell;
  private Cell[] neighbours;

  /**
   * Test setup.
   */
  @BeforeEach
  public void setUp() {
    cell = new Cell();

    neighbours = new Cell[NUMBER_OF_NEIGHBOURS];
    for (int i = 0; i < NUMBER_OF_NEIGHBOURS; i++) {
      neighbours[i] = new Cell();
    }

    cell.setNeighbours(neighbours);
  }

  @Test
  public void setNeighbours_withNull_throwsNullPointerException() {
    assertThrows(NullPointerException.class,
        () -> cell.setNeighbours(null),
        "neighbours is null");
  }

  @Test
  public void toggleAlive_togglesAliveState() {
    assertFalse(cell.isAlive());
    cell.toggleAlive();
    assertTrue(cell.isAlive());
    cell.toggleAlive();
    assertFalse(cell.isAlive());
  }

  @Test
  public void calculateNextState_aliveWith1Neighbour_cellDies() {
    calculateNextStateTest(true, 1, false);
  }

  @Test
  public void calculateNextState_aliveWith2Neighbours_cellLives() {
    calculateNextStateTest(true, 2, true);
  }

  @Test
  public void calculateNextState_aliveWith3Neighbours_cellLives() {
    calculateNextStateTest(true, 3, true);
  }

  @Test
  public void calculateNextState_aliveWith4Neighbours_cellDies() {
    calculateNextStateTest(true, 4, false);
  }

  @Test
  public void calculateNextState_deadWith1Neighbour_cellDies() {
    calculateNextStateTest(false, 1, false);
  }

  @Test
  public void calculateNextState_deadWith2Neighbours_cellDies() {
    calculateNextStateTest(false, 2, false);
  }

  @Test
  public void calculateNextState_deadWith3Neighbours_cellLives() {
    calculateNextStateTest(false, 3, true);
  }

  @Test
  public void calculateNextState_deadWith4Neighbours_cellDies() {
    calculateNextStateTest(false, 4, false);
  }

  private void calculateNextStateTest(boolean isAlive,
      int numberOfAliveNeighbours,
      boolean expectedAlive) {
    // setup
    cell.setAlive(isAlive);

    for (int i = 0; i < numberOfAliveNeighbours; i++) {
      neighbours[i].setAlive(true);
    }

    // execution
    cell.calculateNextState();
    cell.goToNextState();

    // verification
    assertEquals(expectedAlive, cell.isAlive());
  }

}
