package lowe.mike.gameoflife;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.concurrent.TimeUnit;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 * {@link GameOfLifeApplication} tests.
 */
@ExtendWith(ApplicationExtension.class)
public class GameOfLifeApplicationTest {

  /**
   * Test setup.
   *
   * @throws Exception if an exception occurs when launching the application
   */
  @BeforeEach
  public void setUp() throws Exception {
    ApplicationTest.launch(GameOfLifeApplication.class);
  }

  @Test
  public void playButtonClick_startsGameOfLife(FxRobot robot) {
    // initial verification
    verifyThat("#generationNumberLabel", hasText("0"));

    // execution
    robot.clickOn("#playToggleButton");

    // verification
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    verifyThat("#generationNumberLabel", hasText(not("0")));
  }

  @Test
  public void pauseButtonClick_pausesGameOfLife(FxRobot robot) {
    // initial setup
    robot.clickOn("#playToggleButton");
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

    // execution
    robot.clickOn("#pauseToggleButton");

    // verification
    String generationBefore = robot.lookup("#generationNumberLabel").queryLabeled().getText();
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    String generationAfter = robot.lookup("#generationNumberLabel").queryLabeled().getText();
    assertEquals(generationBefore, generationAfter);
  }

  @Test
  public void clearButtonClick_clearsGameOfLife(FxRobot robot) {
    // initial setup
    robot.clickOn("#playToggleButton");
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);

    // execution
    robot.clickOn("#clearButton");

    // verification
    verifyThat("#generationNumberLabel", hasText("0"));
  }

  @Test
  public void slowButtonClick_togglesSlow(FxRobot robot) {
    // initial setup
    ToggleButton mediumButton = robot.lookup("#mediumToggleButton").query();
    robot.clickOn(mediumButton);
    ToggleButton slowButton = robot.lookup("#slowToggleButton").query();

    // execution
    robot.clickOn(slowButton);

    // verification
    assertTrue(slowButton.isSelected());
  }

  @Test
  public void mediumButtonClick_togglesMedium(FxRobot robot) {
    // initial setup
    ToggleButton mediumButton = robot.lookup("#mediumToggleButton").query();

    // execution
    robot.clickOn(mediumButton);

    // verification
    assertTrue(mediumButton.isSelected());
  }

  @Test
  public void fastButtonClick_togglesFast(FxRobot robot) {
    // initial setup
    ToggleButton fastButton = robot.lookup("#fastToggleButton").query();

    // execution
    robot.clickOn(fastButton);

    // verification
    assertTrue(fastButton.isSelected());
  }

  @Test
  public void fastestButtonClick_togglesFastest(FxRobot robot) {
    // initial setup
    ToggleButton fastestButton = robot.lookup("#fastestToggleButton").query();

    // execution
    robot.clickOn(fastestButton);

    // verification
    assertTrue(fastestButton.isSelected());
  }

  @Test
  public void cellClick_togglesAlive(FxRobot robot) {
    // initial setup
    Pane cellPane = robot.lookup(".cellPane").query();
    cellPane.getStyleClass().remove("alive");

    // execution
    robot.clickOn(cellPane);

    // verification
    assertTrue(cellPane.getStyleClass().contains("alive"));

    // execution
    robot.clickOn(cellPane);

    // verification
    assertFalse(cellPane.getStyleClass().contains("alive"));
  }

}
