package lowe.mike.gameoflife.model;

import static java.util.Objects.requireNonNull;
import static lowe.mike.gameoflife.model.Utils.requirePositiveNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * {@code Grid} instances represent the grid in <i>The Game of Life</i>.
 *
 * @author Mike Lowe
 */
public class Grid {

  private final int numberOfRows;
  private final int numberOfColumns;
  private final Cell[][] cells;

  /**
   * Creates a new {@code Grid} instance given the number of rows and columns.
   *
   * @param numberOfRows the number of rows
   * @param numberOfColumns the number of columns
   * @throws IllegalArgumentException if {@code numberOfRows} or {@code numberOfColumns} are
   *     less than or equal to 0
   */
  public Grid(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = requirePositiveNumber(numberOfRows, "number of rows is " + numberOfRows);
    this.numberOfColumns =
        requirePositiveNumber(numberOfColumns, "number of columns is " + numberOfColumns);
    this.cells = createCells();
  }

  private Cell[][] createCells() {
    Cell[][] cells = new Cell[getNumberOfRows()][getNumberOfColumns()];
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        cells[rowIndex][columnIndex] = new Cell();
      }
    }
    return cells;
  }

  /**
   * Returns the {@link Cell} at the given index.
   *
   * <p>Note that the index is wrapped around so that a {@link Cell} is always returned.
   *
   * @param rowIndex the row index of the {@link Cell}
   * @param columnIndex the column index of the {@link Cell}
   * @return the {@link Cell} at the given row and column index
   */
  public Cell getCell(int rowIndex, int columnIndex) {
    return cells[getWrappedRowIndex(rowIndex)][getWrappedColumnIndex(columnIndex)];
  }

  private int getWrappedRowIndex(int rowIndex) {
    return (rowIndex + getNumberOfRows()) % getNumberOfRows();
  }

  private int getWrappedColumnIndex(int columnIndex) {
    return (columnIndex + getNumberOfColumns()) % getNumberOfColumns();
  }

  /**
   * Returns the number of rows in this {@code Grid}.
   *
   * @return the number of rows in this {@code Grid}
   */
  public int getNumberOfRows() {
    return numberOfRows;
  }

  /**
   * Returns the number of columns in this {@code Grid}.
   *
   * @return the number of columns in this {@code Grid}
   */
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  /**
   * Transitions all {@link Cell}s in this {@code Grid} to the next generation.
   *
   * <p>The following rules are applied:
   * <ul>
   * <li>Any live {@link Cell} with fewer than two live neighbours dies, i.e. underpopulation.</li>
   * <li>Any live {@link Cell} with two or three live neighbours lives on to the next
   * generation.</li>
   * <li>Any live {@link Cell} with more than three live neighbours dies, i.e. overpopulation.</li>
   * <li>Any dead {@link Cell} with exactly three live neighbours becomes a live cell, i.e.
   * reproduction.</li>
   * </ul>
   */
  public void nextGeneration() {
    goToNextState(calculateNextState());
  }

  private boolean[][] calculateNextState() {
    boolean[][] nextState = new boolean[getNumberOfRows()][getNumberOfColumns()];

    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        Cell cell = getCell(rowIndex, columnIndex);
        int numberOfAliveNeighbours = countAliveNeighbours(rowIndex, columnIndex);
        boolean isAliveInNextState =
            ((cell.isAlive() && numberOfAliveNeighbours == 2) || numberOfAliveNeighbours == 3);
        nextState[rowIndex][columnIndex] = isAliveInNextState;
      }
    }

    return nextState;
  }

  private int countAliveNeighbours(int rowIndex, int columnIndex) {
    return (int) getNeighbours(rowIndex, columnIndex)
        .stream()
        .filter(Cell::isAlive)
        .count();
  }

  private List<Cell> getNeighbours(int rowIndex, int columnIndex) {
    int north = rowIndex - 1;
    int east = columnIndex + 1;
    int south = rowIndex + 1;
    int west = columnIndex - 1;

    return Arrays.asList(
        getCell(north, west),
        getCell(north, columnIndex),
        getCell(north, east),
        getCell(rowIndex, east),
        getCell(south, east),
        getCell(south, columnIndex),
        getCell(south, west),
        getCell(rowIndex, west)
    );
  }

  private void goToNextState(boolean[][] nextState) {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(nextState[rowIndex][columnIndex]);
      }
    }
  }

  /**
   * Sets all {@link Cell}s in this {@code Grid} as dead.
   */
  public void clear() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(false);
      }
    }
  }

  /**
   * Goes through each {@link Cell} in this {@code Grid} and randomly sets it as alive or dead.
   *
   * @param random {@link Random} instance used to decide if each {@link Cell} is alive or dead
   * @throws NullPointerException if {@code random} is {@code null}
   */
  public void randomGeneration(Random random) {
    requireNonNull(random, "random is null");
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        getCell(rowIndex, columnIndex).setAlive(random.nextBoolean());
      }
    }
  }
}
