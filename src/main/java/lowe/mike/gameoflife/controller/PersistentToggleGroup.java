package lowe.mike.gameoflife.controller;

import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

/**
 * An extension of {@link ToggleGroup} that ensures that a {@link Toggle} in a
 * group must always be selected.
 *
 * @author Mike Lowe
 */
final class PersistentToggleGroup extends ToggleGroup {

  /**
   * Creates a new {@code PersistentToggleGroup}.
   */
  PersistentToggleGroup() {
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
