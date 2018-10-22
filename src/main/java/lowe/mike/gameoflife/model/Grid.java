package lowe.mike.gameoflife.model;

import static java.util.Objects.requireNonNull;
import static lowe.mike.gameoflife.model.Util.requirePositiveNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * {@code Grid} instances represent the grid in <i>The Game of Life</i>.
 *
 * @author Mike Lowe
 */
public class Grid {

  private final int numberOfRows;
  private final int numberOfColumns;
  private final Random random;
  private final List<List<Cell>> cells = new ArrayList<>();

  /**
   * Creates a new {@code Grid} instance given the number of rows/columns and the {@link Random}
   * instance used to determine the state of {@link Cell}s when creating random generations.
   *
   * @param numberOfRows the number of rows
   * @param numberOfColumns the number of columns
   * @param random {@link Random} used to determine the state of {@link Cell}s when creating
   *     random generations
   * @throws IllegalArgumentException if {@code numberOfRows} or {@code numberOfColumns} are
   *     less than or equal to 0
   * @throws NullPointerException if {@code random} is {@code null}
   */
  public Grid(int numberOfRows, int numberOfColumns, Random random) {
    this.numberOfRows = requirePositiveNumber(numberOfRows, "number of rows is " + numberOfRows);
    this.numberOfColumns =
        requirePositiveNumber(numberOfColumns, "number of columns is " + numberOfColumns);
    this.random = requireNonNull(random, "random is null");
    initializeCells();
  }

  private void initializeCells() {
    createNewCells();
    setCellNeighbours();
  }

  private void createNewCells() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      List<Cell> row = new ArrayList<>();
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        row.add(new Cell());
      }
      cells.add(row);
    }
  }

  private void setCellNeighbours() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        Set<Cell> neighbours = getNeighbours(rowIndex, columnIndex);
        Cell cell = getCell(rowIndex, columnIndex);
        cell.setNeighbours(neighbours);
      }
    }
  }

  private Set<Cell> getNeighbours(int rowIndex, int columnIndex) {
    int north = rowIndex - 1;
    int east = columnIndex + 1;
    int south = rowIndex + 1;
    int west = columnIndex - 1;

    Set<Cell> neighbours = new HashSet<>(Arrays.asList(
        getCell(north, west),
        getCell(north, columnIndex),
        getCell(north, east),
        getCell(rowIndex, east),
        getCell(south, east),
        getCell(south, columnIndex),
        getCell(south, west),
        getCell(rowIndex, west)
    ));

    neighbours.removeIf(Objects::isNull);

    return neighbours;
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
    return cells.get(getWrappedRowIndex(rowIndex)).get(getWrappedColumnIndex(columnIndex));
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
   */
  public void nextGeneration() {
    cells.forEach(row -> row.forEach(Cell::calculateNextState));
    cells.forEach(row -> row.forEach(Cell::goToNextState));
  }

  /**
   * Sets all {@link Cell}s in this {@code Grid} as dead.
   */
  public void clear() {
    cells.forEach(row -> row.forEach(cell -> cell.setAlive(false)));
  }

  /**
   * Goes through each {@link Cell} in this {@code Grid} and randomly sets it as alive or dead.
   */
  public void randomGeneration() {
    cells.forEach(row -> row.forEach(cell -> cell.setAlive(random.nextBoolean())));
  }

}
