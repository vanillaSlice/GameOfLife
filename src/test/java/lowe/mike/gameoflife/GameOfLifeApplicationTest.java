package lowe.mike.gameoflife;

import static org.hamcrest.CoreMatchers.not;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.concurrent.TimeUnit;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

/**
 * {@link GameOfLifeApplication} tests.
 */
@ExtendWith(ApplicationExtension.class)
public class GameOfLifeApplicationTest {

  @Start
  void onStart(Stage stage) {
    new GameOfLifeApplication().start(stage);
  }

  @Test
  void playButtonClick_startsGameOfLife(FxRobot robot) {
    // initial verification
    verifyThat("#generationNumberLabel", hasText("0"));

    // execution
    robot.clickOn("#playToggleButton");

    // verification
    WaitForAsyncUtils.sleep(2, TimeUnit.SECONDS);
    verifyThat("#generationNumberLabel", hasText(not("0")));
  }

}
