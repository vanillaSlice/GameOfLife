package lowe.mike.gameoflife.controller;

import static lowe.mike.gameoflife.model.Speed.FAST;
import static lowe.mike.gameoflife.model.Speed.FASTEST;
import static lowe.mike.gameoflife.model.Speed.MEDIUM;
import static lowe.mike.gameoflife.model.Speed.SLOW;

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

/**
 * Controller for <i>The Game of Life</i> application.
 * 
 * @author Mike Lowe
 */
public final class Controller {

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

		for (int rowIndex = 0; rowIndex < gameOfLife.getGrid().getNumberOfRows(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < gameOfLife.getGrid().getNumberOfColumns(); columnIndex++) {
				
				
				Pane rectangle = new Pane();
						rectangle.setPrefSize(14, 14);
						GridPane.setFillHeight(rectangle, true);
						GridPane.setFillWidth(rectangle, true);
				rectangle.getStyleClass().add("cell");

				gameOfLife.getGrid().getCell(rowIndex, columnIndex).aliveProperty()
						.addListener((observable, oldValue, newValue) -> {
							if (newValue)
								rectangle.getStyleClass().add("alive");
							else
								rectangle.getStyleClass().remove("alive");
						});

				Cell cell = gameOfLife.getGrid().getCell(rowIndex, columnIndex);

				rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
					cell.toggleAlive();
				});
				

				gridPane.add(rectangle, columnIndex, rowIndex);
			}
		}
	}

	private void setGenerationNumberLabelTextProperty() {
		generationNumberLabel.textProperty().bind(gameOfLife.generationProperty().asString());
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