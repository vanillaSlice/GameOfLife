package lowe.mike.gameoflife.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
   * Creates a new {@code Grid} instance given the number of rows and columns to add.
   *
   * @param numberOfRows the number of rows
   * @param numberOfColumns the number of columns
   * @throws NegativeArraySizeException if {@code numberOfRows} or {@code numberOfColumns} are
   *     less than 0
   */
  public Grid(int numberOfRows, int numberOfColumns) {
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.cells = new Cell[this.numberOfRows][this.numberOfColumns];
    initializeCells();
  }

  private void initializeCells() {
    createNewCells();
    setCellNeighbours();
  }

  private void createNewCells() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        cells[rowIndex][columnIndex] = new Cell();
      }
    }
  }

  private void setCellNeighbours() {
    for (int rowIndex = 0; rowIndex < getNumberOfRows(); rowIndex++) {
      for (int columnIndex = 0; columnIndex < getNumberOfColumns(); columnIndex++) {
        Cell[] neighbours = getNeighbours(rowIndex, columnIndex);
        Cell cell = getCell(rowIndex, columnIndex);
        cell.setNeighbours(neighbours);
      }
    }
  }

  private Cell[] getNeighbours(int rowIndex, int columnIndex) {
    List<Cell> neighbours = new ArrayList<>();

    int north = rowIndex - 1;
    int east = columnIndex + 1;
    int south = rowIndex + 1;
    int west = columnIndex - 1;

    neighbours.add(getCell(north, west));
    neighbours.add(getCell(north, columnIndex));
    neighbours.add(getCell(north, east));
    neighbours.add(getCell(rowIndex, east));
    neighbours.add(getCell(south, east));
    neighbours.add(getCell(south, columnIndex));
    neighbours.add(getCell(south, west));
    neighbours.add(getCell(rowIndex, west));

    neighbours.removeIf(Objects::isNull);

    return neighbours.toArray(new Cell[0]);
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
   */
  public void nextGeneration() {
    calculateCellsNextState();
    transitionCellsToNextState();
  }

  private void calculateCellsNextState() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        cell.calculateNextState();
      }
    }
  }

  private void transitionCellsToNextState() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        cell.goToNextState();
      }
    }
  }

  /**
   * Sets all {@link Cell}s in this {@code Grid} as dead.
   */
  public void clear() {
    for (Cell[] row : cells) {
      for (Cell cell : row) {
        cell.setAlive(false);
      }
    }
  }

}
