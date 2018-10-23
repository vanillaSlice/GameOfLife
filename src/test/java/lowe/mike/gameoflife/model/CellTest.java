package lowe.mike.gameoflife.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * {@link Cell} tests.
 *
 * @author Mike Lowe
 */
public class CellTest {

  @Test
  public void toggleAlive_togglesAliveState() {
    // setup
    Cell cell = new Cell();

    // execution and verification
    assertFalse(cell.isAlive());
    cell.toggleAlive();
    assertTrue(cell.isAlive());
    cell.toggleAlive();
    assertFalse(cell.isAlive());
  }

}
