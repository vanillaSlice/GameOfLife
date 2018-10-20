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

  private Cell cell;
  private Cell[] neighbours;

  /**
   * Test setup.
   */
  @BeforeEach
  public void setUp() {
    cell = new Cell();

    neighbours = new Cell[4];
    for (int i = 0; i < 4; i++) {
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
    cell.setAlive(false);
    assertFalse(cell.isAlive());
    cell.toggleAlive();
    assertTrue(cell.isAlive());
    cell.toggleAlive();
    assertFalse(cell.isAlive());
  }

  @Test
  public void calculateNextState_aliveWith1Neighbour_cellDies() {
    verifyNextState(true, 1, false);
  }

  @Test
  public void calculateNextState_aliveWith2Neighbours_cellLives() {
    verifyNextState(true, 2, true);
  }

  @Test
  public void calculateNextState_aliveWith3Neighbours_cellLives() {
    verifyNextState(true, 3, true);
  }

  @Test
  public void calculateNextState_aliveWith4Neighbours_cellDies() {
    verifyNextState(true, 4, false);
  }

  @Test
  public void calculateNextState_deadWith1Neighbour_cellDies() {
    verifyNextState(false, 1, false);
  }

  @Test
  public void calculateNextState_deadWith2Neighbours_cellDies() {
    verifyNextState(false, 2, false);
  }

  @Test
  public void calculateNextState_deadWith3Neighbours_cellLives() {
    verifyNextState(false, 3, true);
  }

  @Test
  public void calculateNextState_deadWith4Neighbours_cellDies() {
    verifyNextState(false, 4, false);
  }

  private void verifyNextState(boolean isAlive,
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
