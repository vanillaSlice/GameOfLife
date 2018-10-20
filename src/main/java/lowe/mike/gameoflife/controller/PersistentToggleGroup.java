package lowe.mike.gameoflife.controller;

import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * An extension of {@link ToggleGroup} that ensures that a {@link Toggle} in a group must always be
 * selected.
 *
 * @author Mike Lowe
 */
public class PersistentToggleGroup extends ToggleGroup {

  /**
   * Creates a new {@code PersistentToggleGroup}.
   */
  public PersistentToggleGroup() {
    addTogglesListener();
  }

  private void addTogglesListener() {
    getToggles().addListener((Change<? extends Toggle> change) -> {
      while (change.next()) {
        for (Toggle toggle : change.getAddedSubList()) {
          ToggleButton toggleButton = (ToggleButton) toggle;
          toggleButton.addEventFilter(MOUSE_RELEASED, mouseEvent -> {
            if (toggleButton.equals(getSelectedToggle())) {
              mouseEvent.consume();
            }
          });
        }
      }
    });
  }

}
