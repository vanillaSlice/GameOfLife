package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@link Cell} tests.
 *
 * @author Mike Lowe
 */
public class CellTest {

  private Cell cell;
  private Set<Cell> neighbours;

  /**
   * Test setup.
   */
  @BeforeEach
  public void setUp() {
    cell = new Cell();

    neighbours = new HashSet<>(Arrays.asList(new Cell(), new Cell(), new Cell(), new Cell()));

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
    // execution and verification
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
  public void calculateNextState_aliveWith2Neighbours_cellStaysAlive() {
    verifyNextState(true, 2, true);
  }

  @Test
  public void calculateNextState_aliveWith3Neighbours_cellStaysAlive() {
    verifyNextState(true, 3, true);
  }

  @Test
  public void calculateNextState_aliveWith4Neighbours_cellDies() {
    verifyNextState(true, 4, false);
  }

  @Test
  public void calculateNextState_deadWith1Neighbour_cellStaysDead() {
    verifyNextState(false, 1, false);
  }

  @Test
  public void calculateNextState_deadWith2Neighbours_cellStaysDead() {
    verifyNextState(false, 2, false);
  }

  @Test
  public void calculateNextState_deadWith3Neighbours_cellComesAlive() {
    verifyNextState(false, 3, true);
  }

  @Test
  public void calculateNextState_deadWith4Neighbours_cellStaysDead() {
    verifyNextState(false, 4, false);
  }

  private void verifyNextState(boolean isAlive,
      int numberOfAliveNeighbours,
      boolean expectedAlive) {
    // setup
    cell.setAlive(isAlive);

    Iterator<Cell> neighbourIterator = neighbours.iterator();
    for (int i = 0; i < numberOfAliveNeighbours; i++) {
      neighbourIterator.next().setAlive(true);
    }

    // execution
    cell.calculateNextState();
    cell.goToNextState();

    // verification
    assertEquals(expectedAlive, cell.isAlive());
  }

}
