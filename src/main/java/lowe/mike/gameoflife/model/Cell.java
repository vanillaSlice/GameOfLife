package lowe.mike.gameoflife.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * {@code Cell} instances represent cells that make up a grid in <i>The Game of Life</i>.
 *
 * @author Mike Lowe
 */
public class Cell {

  private final BooleanProperty aliveProperty = new SimpleBooleanProperty();

  /**
   * Returns if this {@code Cell} is alive.
   *
   * @return {@code true} if this {@code Cell} is alive; {@code false} if it is dead
   */
  public boolean isAlive() {
    return aliveProperty().get();
  }

  /**
   * Sets if this {@code Cell} is alive.
   *
   * @param isAlive {@code true} if this {@code Cell} is alive; {@code false} if it is dead
   */
  public void setAlive(boolean isAlive) {
    aliveProperty().set(isAlive);
  }

  /**
   * Sets this {@code Cell} as alive if it is dead; or sets it as dead if it is alive.
   */
  public void toggleAlive() {
    setAlive(!isAlive());
  }

  /**
   * Returns this {@code Cell}'s alive {@link BooleanProperty}.
   *
   * @return this {@code Cell}'s alive {@link BooleanProperty}
   */
  public BooleanProperty aliveProperty() {
    return aliveProperty;
  }
}
