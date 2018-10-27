package lowe.mike.gameoflife;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lowe.mike.gameoflife.model.GameOfLife;
import lowe.mike.gameoflife.model.Grid;
import lowe.mike.gameoflife.model.Speed;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 * {@link GameOfLifeApplication} tests.
 */
public class GameOfLifeApplicationTest extends ApplicationTest {

  private GameOfLife gameOfLife;

  @Override
  public void start(Stage stage) {
    gameOfLife = spy(new GameOfLife(new Grid(40, 70)));
    new GameOfLifeApplication(gameOfLife).start(stage);
  }

  @Test
  public void playButtonClick_startsGameOfLife() {
    // initial verification
    verifyThat("#generationNumberLabel", hasText("0"));

    // execution
    clickOn("#playToggleButton");

    // verification
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    verifyThat("#generationNumberLabel", hasText(not("0")));
    verify(gameOfLife).play();
  }

  @Test
  public void pauseButtonClick_pausesGameOfLife() {
    // initial setup
    clickOn("#playToggleButton");
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

    // execution
    clickOn("#pauseToggleButton");

    // verification
    String generationBefore = lookup("#generationNumberLabel").queryLabeled().getText();
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    String generationAfter = lookup("#generationNumberLabel").queryLabeled().getText();
    assertEquals(generationBefore, generationAfter);
    verify(gameOfLife).pause();
  }

  @Test
  public void resetButtonClick_resetsGameOfLife() {
    // initial setup
    clickOn("#playToggleButton");
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

    // execution
    clickOn("#resetButton");

    // verification
    verifyThat("#generationNumberLabel", hasText("0"));
    verify(gameOfLife).reset();
  }

  @Test
  public void clearButtonClick_clearsGameOfLife() {
    // initial setup
    clickOn("#playToggleButton");
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

    // execution
    clickOn("#clearButton");

    // verification
    verifyThat("#generationNumberLabel", hasText("0"));
    verify(gameOfLife).clear();
  }

  @Test
  public void slowButtonClick_togglesSlow() {
    // initial setup
    ToggleButton mediumButton = lookup("#mediumToggleButton").query();
    clickOn(mediumButton);
    ToggleButton slowButton = lookup("#slowToggleButton").query();

    // execution
    clickOn(slowButton);

    // verification
    assertTrue(slowButton.isSelected());
    verify(gameOfLife).setSpeed(Speed.SLOW);
  }

  @Test
  public void mediumButtonClick_togglesMedium() {
    // initial setup
    ToggleButton mediumButton = lookup("#mediumToggleButton").query();

    // execution
    clickOn(mediumButton);

    // verification
    assertTrue(mediumButton.isSelected());
    verify(gameOfLife).setSpeed(Speed.MEDIUM);
  }

  @Test
  public void fastButtonClick_togglesFast() {
    // initial setup
    ToggleButton fastButton = lookup("#fastToggleButton").query();

    // execution
    clickOn(fastButton);

    // verification
    assertTrue(fastButton.isSelected());
    verify(gameOfLife).setSpeed(Speed.FAST);
  }

  @Test
  public void fastestButtonClick_togglesFastest() {
    // initial setup
    ToggleButton fastestButton = lookup("#fastestToggleButton").query();

    // execution
    clickOn(fastestButton);

    // verification
    assertTrue(fastestButton.isSelected());
    verify(gameOfLife).setSpeed(Speed.FASTEST);
  }

  @Test
  public void cellClick_togglesAlive() {
    // initial setup
    Pane cellPane = lookup(".cell-pane")
        .lookup((Predicate<Pane>) p -> !p.getStyleClass().contains("alive"))
        .query();

    // execution
    clickOn(cellPane);

    // verification
    assertTrue(cellPane.getStyleClass().contains("alive"));

    // execution
    clickOn(cellPane);

    // verification
    assertFalse(cellPane.getStyleClass().contains("alive"));
  }

}
