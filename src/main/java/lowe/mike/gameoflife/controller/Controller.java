package lowe.mike.gameoflife.controller;

import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.layout.GridPane.setFillHeight;
import static javafx.scene.layout.GridPane.setFillWidth;
import static lowe.mike.gameoflife.model.Speed.FAST;
import static lowe.mike.gameoflife.model.Speed.FASTEST;
import static lowe.mike.gameoflife.model.Speed.MEDIUM;
import static lowe.mike.gameoflife.model.Speed.SLOW;

import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lowe.mike.gameoflife.model.Cell;
import lowe.mike.gameoflife.model.GameOfLife;
import lowe.mike.gameoflife.model.Grid;

/**
 * Controller for <i>The Game of Life</i> application.
 * 
 * @author Mike Lowe
 */
public final class Controller {

	private static final double CELL_SIZE = 14;
	private static final String CELL_PANE_STYLE_CLASS = "cellPane";
	private static final String ALIVE_STYLE_CLASS = "alive";

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
		toggleGroup.getToggles().addAll(slowToggleButton, mediumToggleButton, fastToggleButton, fastestToggleButton);
		slowToggleButton.setSelected(true);
	}

	public void setGameOfLife(GameOfLife gameOfLife) {
		this.gameOfLife = gameOfLife;
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

		for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++)
			for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++)
				addCellPane(rowIndex, columnIndex);
	}

	private void addCellPane(int rowIndex, int columnIndex) {
		Pane cellPane = new Pane();

		addCellPaneStyle(cellPane);
		addAlivePropertyListener(rowIndex, columnIndex, cellPane);
		addClickEventHandler(rowIndex, columnIndex, cellPane);

		gridPane.add(cellPane, columnIndex, rowIndex);
	}

	private void addCellPaneStyle(Pane cellPane) {
		cellPane.setPrefSize(CELL_SIZE, CELL_SIZE);
		setFillHeight(cellPane, true);
		setFillWidth(cellPane, true);
		cellPane.getStyleClass().add(CELL_PANE_STYLE_CLASS);
	}

	private void addAlivePropertyListener(int rowIndex, int columnIndex, Pane cellPane) {
		BooleanProperty aliveProperty = gameOfLife.getGrid().getCell(rowIndex, columnIndex).aliveProperty();
		aliveProperty.addListener((observable, oldValue, newValue) -> {
			ObservableList<String> styleClass = cellPane.getStyleClass();
			if (newValue)
				styleClass.add(ALIVE_STYLE_CLASS);
			else
				styleClass.remove(ALIVE_STYLE_CLASS);
		});
	}

	private void addClickEventHandler(int rowIndex, int columnIndex, Pane cellPane) {
		Cell cell = gameOfLife.getGrid().getCell(rowIndex, columnIndex);
		cellPane.addEventHandler(MOUSE_CLICKED, event -> cell.toggleAlive());
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
	private void clearButtonAction() {
		gameOfLife.clear();
		pauseToggleButton.setSelected(true);
	}

	@FXML
	private void slowToggleButtonAction() {
		gameOfLife.setSpeed(SLOW);
	}

	@FXML
	private void mediumToggleButtonAction() {
		gameOfLife.setSpeed(MEDIUM);
	}

	@FXML
	private void fastToggleButtonAction() {
		gameOfLife.setSpeed(FAST);
	}

	@FXML
	private void fastestToggleButtonAction() {
		gameOfLife.setSpeed(FASTEST);
	}

}