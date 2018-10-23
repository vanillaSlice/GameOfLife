package lowe.mike.gameoflife;

import static javafx.application.Platform.exit;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lowe.mike.gameoflife.controller.Controller;
import lowe.mike.gameoflife.model.GameOfLife;
import lowe.mike.gameoflife.model.Grid;

/**
 * Entry point for <i>The Game of Life</i> application.
 *
 * @author Mike Lowe
 */
public class GameOfLifeApplication extends Application {

  private static final String APP_NAME = "Game of Life";
  private static final int NUMBER_OF_ROWS = 40;
  private static final int NUMBER_OF_COLUMNS = 70;
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final Image ICON_64X64 = new Image("/view/icon-64x64.png");
  private static final Image ICON_32X32 = new Image("/view/icon-32x32.png");
  private static final Image ICON_16X16 = new Image("/view/icon-16x16.png");

  private GameOfLife gameOfLife;
  private Stage primaryStage;
  private Parent view;

  @Override
  public void start(Stage primaryStage) {
    initializeGameOfLife();
    initializePrimaryStage(primaryStage);
    initializeView();
    addIcons();
    showScene();
  }

  private void initializeGameOfLife() {
    gameOfLife = new GameOfLife(new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
  }

  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  private void initializeView() {
    FXMLLoader loader = new FXMLLoader();
    URL location = GameOfLifeApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loadFxml(loader);
    Controller controller = loader.getController();
    controller.setGameOfLife(gameOfLife);
  }

  private static <T> T loadFxml(FXMLLoader loader) {
    try {
      return loader.load();
    } catch (IOException e) {
      throw new AssertionError("Must always be able to load FXML resource");
    }
  }

  private void addIcons() {
    primaryStage.getIcons().addAll(ICON_64X64, ICON_32X32, ICON_16X16);
  }

  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
