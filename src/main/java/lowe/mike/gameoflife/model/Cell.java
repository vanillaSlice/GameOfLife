package lowe.mike.gameoflife.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * {@code Cell} instances represent cells that make up a grid in <i>The Game of
 * Life</i>.
 *
 * @author Mike Lowe
 */
public final class Cell {

    private Cell[] neighbours;
    private final BooleanProperty isAlive = new SimpleBooleanProperty();
    private boolean isAliveInNextState;

    /**
     * Creates a new {@code Cell} instance.
     */
    Cell() {
    }

    /**
     * Sets this {@code Cell}'s array of neighbouring {@code Cell}s.
     *
     * @param neighbours the array of neighbouring {@code Cell}s
     */
    void setNeighbours(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * @return {@code true} if this {@code Cell} is alive; {@code false} if it
     * is dead
     */
    public boolean isAlive() {
        return isAlive.get();
    }

    /**
     * @param isAlive {@code true} if this {@code Cell} is alive; {@code false} if
     *                it is dead
     */
    public void setAlive(boolean isAlive) {
        this.isAlive.set(isAlive);
    }

    /**
     * Sets this {@code Cell} as alive if it is dead; or sets it as dead if it
     * is alive.
     */
    public void toggleAlive() {
        setAlive(!isAlive());
    }

    /**
     * @return this {@code Cell}'s alive {@link BooleanProperty}
     */
    public BooleanProperty aliveProperty() {
        return isAlive;
    }

    /**
     * Calculates this {@code Cell}'s next state by applying the rules of <i>The
     * Game of Life</i>.
     * <p>
     * The four rules of <i>The Game of Life</i> are:
     * <ul>
     * <li>Any live {@link Cell} with fewer than two live neighbours dies, i.e.
     * under population.</li>
     * <li>Any live {@link Cell} with two or three live neighbours lives on to
     * the next generation.</li>
     * <li>Any live {@link Cell} with more than three live neighbours dies, i.e.
     * overpopulation.</li>
     * <li>Any dead {@link Cell} with exactly three live neighbours becomes a
     * live cell, i.e. reproduction.</li>
     * </ul>
     */
    void calculateNextState() {
        int numberOfAliveNeighbours = countNumberOfAliveNeighbours();

        if (isAlive())
            setAliveInNextState(numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3);
        else
            setAliveInNextState(numberOfAliveNeighbours == 3);
    }

    private int countNumberOfAliveNeighbours() {
        int count = 0;

        for (Cell neighbour : neighbours)
            if (neighbour.isAlive())
                count++;

        return count;
    }

    private void setAliveInNextState(boolean isAliveInNextState) {
        this.isAliveInNextState = isAliveInNextState;
    }

    /**
     * Transitions this {@code Cell} to the next state.
     */
    void goToNextState() {
        setAlive(isAliveInNextState);
    }

    @Override
    public String toString() {
        return Boolean.toString(isAlive());
    }

}