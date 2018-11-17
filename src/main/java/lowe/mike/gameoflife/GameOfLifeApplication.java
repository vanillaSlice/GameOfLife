package lowe.mike.gameoflife;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
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

  static final int NUMBER_OF_ROWS = 40;
  static final int NUMBER_OF_COLUMNS = 70;

  private static final String APP_NAME = "Game of Life";
  private static final String VIEW_RESOURCE_PATH = "/view/view.fxml";
  private static final List<String> ICON_PATHS = Arrays.asList("/view/icon-16x16.png",
      "/view/icon-32x32.png",
      "/view/icon-64x64.png");

  private final GameOfLife gameOfLife;
  private Stage primaryStage;
  private Parent view;

  /**
   * Creates a new {@code GameOfLifeApplication} instance.
   */
  public GameOfLifeApplication() {
    this(new GameOfLife(new Grid(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS)));
  }

  /**
   * Creates a new {@code GameOfLifeApplication} instance given a {@link GameOfLife} instance.
   *
   * @param gameOfLife the {@link GameOfLife} instance
   * @throws NullPointerException if {@code gameOfLife} is {@code null}
   */
  GameOfLifeApplication(GameOfLife gameOfLife) {
    this.gameOfLife = requireNonNull(gameOfLife, "game of life is null");
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    initializePrimaryStage(primaryStage);
    initializeView();
    addIcons();
    showScene();
  }

  private void initializePrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    this.primaryStage.setOnCloseRequest(event -> Platform.exit());
    this.primaryStage.setResizable(false);
    this.primaryStage.sizeToScene();
  }

  private void initializeView() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    URL location = GameOfLifeApplication.class.getResource(VIEW_RESOURCE_PATH);
    loader.setLocation(location);
    view = loader.load();
    Controller controller = loader.getController();
    controller.setGameOfLife(gameOfLife);
  }

  private void addIcons() {
    ICON_PATHS.forEach(path -> primaryStage.getIcons().add(new Image(path)));
  }

  private void showScene() {
    Scene scene = new Scene(view);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
