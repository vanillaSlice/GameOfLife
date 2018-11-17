package lowe.mike.gameoflife.controller;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lowe.mike.gameoflife.model.Cell;
import lowe.mike.gameoflife.model.GameOfLife;
import lowe.mike.gameoflife.model.Grid;
import lowe.mike.gameoflife.model.Speed;

/**
 * Controller for <i>The Game of Life</i> application.
 *
 * @author Mike Lowe
 */
public class Controller {

  public static final String CELL_PANE_STYLE_CLASS = "cell-pane";
  public static final String ALIVE_STYLE_CLASS = "alive";

  private static final double CELL_SIZE = 14;

  @FXML
  private AnchorPane rootPane;
  @FXML
  private ToggleButton playToggleButton;
  @FXML
  private ToggleButton pauseToggleButton;
  @FXML
  private ToggleButton slowToggleButton;
  @FXML
  private ToggleButton mediumToggleButton;
  @FXML
  private ToggleButton fastToggleButton;
  @FXML
  private ToggleButton fastestToggleButton;
  @FXML
  private Label generationNumberLabel;
  @FXML
  private GridPane gridPane;

  private GameOfLife gameOfLife;

  @FXML
  private void initialize() {
    initializePlayAndPauseToggleButtons();
    initializeSpeedToggleButtons();
  }

  private void initializePlayAndPauseToggleButtons() {
    ToggleGroup toggleGroup = new PersistentToggleGroup();
    toggleGroup.getToggles().addAll(playToggleButton, pauseToggleButton);
    pauseToggleButton.setSelected(true);
  }

  private void initializeSpeedToggleButtons() {
    ToggleGroup toggleGroup = new PersistentToggleGroup();
    toggleGroup.getToggles()
        .addAll(slowToggleButton, mediumToggleButton, fastToggleButton, fastestToggleButton);
    slowToggleButton.setSelected(true);
  }

  /**
   * Sets {@link GameOfLife} instance.
   *
   * @param gameOfLife {@link GameOfLife} instance
   * @throws NullPointerException if {@code gameOfLife} is {@code null}
   */
  public void setGameOfLife(GameOfLife gameOfLife) {
    this.gameOfLife = requireNonNull(gameOfLife, "game of life is null");
    setGenerationNumberLabelTextProperty();
    initializeGridPane();
  }

  private void setGenerationNumberLabelTextProperty() {
    generationNumberLabel.textProperty().bind(gameOfLife.generationProperty().asString());
  }

  private void initializeGridPane() {
    Grid grid = gameOfLife.getGrid();
    int numberOfRows = grid.getNumberOfRows();
    int numberOfColumns = grid.getNumberOfColumns();

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
        addCellPane(rowIndex, columnIndex);
      }
    }
  }

  private void addCellPane(int rowIndex, int columnIndex) {
    Pane cellPane = new Pane();

    addCellPaneStyle(cellPane);
    addAlivePropertyListener(rowIndex, columnIndex, cellPane);
    setAliveStyle(cellPane, gameOfLife.getGrid().getCell(rowIndex, columnIndex).isAlive());
    addClickEventHandler(rowIndex, columnIndex, cellPane);

    gridPane.add(cellPane, columnIndex, rowIndex);
  }

  private void addCellPaneStyle(Pane cellPane) {
    cellPane.setPrefSize(CELL_SIZE, CELL_SIZE);
    GridPane.setFillHeight(cellPane, true);
    GridPane.setFillWidth(cellPane, true);
    cellPane.getStyleClass().add(CELL_PANE_STYLE_CLASS);
  }

  private void addAlivePropertyListener(int rowIndex, int columnIndex, Pane cellPane) {
    BooleanProperty aliveProperty = gameOfLife.getGrid().getCell(rowIndex, columnIndex)
        .aliveProperty();
    aliveProperty.addListener((observable, oldValue, newValue) ->
        setAliveStyle(cellPane, newValue));
  }

  private void setAliveStyle(Pane cellPane, boolean isAlive) {
    ObservableList<String> styleClass = cellPane.getStyleClass();
    if (isAlive) {
      styleClass.add(ALIVE_STYLE_CLASS);
    } else {
      styleClass.remove(ALIVE_STYLE_CLASS);
    }
  }

  private void addClickEventHandler(int rowIndex, int columnIndex, Pane cellPane) {
    Cell cell = gameOfLife.getGrid().getCell(rowIndex, columnIndex);
    cellPane.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> cell.toggleAlive());
  }

  @FXML
  private void playToggleButtonAction() {
    gameOfLife.play();
  }

  @FXML
  private void pauseToggleButtonAction() {
    gameOfLife.pause();
  }

  @FXML
  private void resetButtonAction() {
    gameOfLife.reset();
    pauseToggleButton.setSelected(true);
  }

  @FXML
  private void clearButtonAction() {
    gameOfLife.clear();
    pauseToggleButton.setSelected(true);
  }

  @FXML
  private void slowToggleButtonAction() {
    gameOfLife.setSpeed(Speed.SLOW);
  }

  @FXML
  private void mediumToggleButtonAction() {
    gameOfLife.setSpeed(Speed.MEDIUM);
  }

  @FXML
  private void fastToggleButtonAction() {
    gameOfLife.setSpeed(Speed.FAST);
  }

  @FXML
  private void fastestToggleButtonAction() {
    gameOfLife.setSpeed(Speed.FASTEST);
  }

}
