package lowe.mike.gameoflife;

import static javafx.application.Platform.exit;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lowe.mike.gameoflife.controller.Controller;
import lowe.mike.gameoflife.model.GameOfLife;

/**
 * Entry point for <i>The Game of Life</i> application.
 * 
 * @author Mike Lowe
 */
public final class GameOfLifeApplication extends Application {

	//add icons for windows, mac and linux
	
	private static final String APP_NAME = "Game of Life";
	private static final int NUMBER_OF_ROWS = 40;
	private static final int NUMBER_OF_COLUMNS = 60;
	private static final String VIEW_RESOURCE_PATH = "/view/View.fxml";

	private GameOfLife gameOfLife;
	private Stage primaryStage;
	private Parent view;

	@Override
	public void start(Stage primaryStage) {
		initializeGameOfLife();
		initializePrimaryStage(primaryStage);
		initializeView();
		showScene();
	}

	private void initializeGameOfLife() {
		gameOfLife = new GameOfLife(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);
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
		view = loadFXML(loader);
		Controller controller = loader.getController();
		controller.setGameOfLife(gameOfLife);
	}

	private static <T> T loadFXML(FXMLLoader loader) {
		try {
			return loader.load();
		} catch (IOException e) {
			throw new AssertionError("Must always be able to load FXML resource");
		}
	}

	private void showScene() {
		Scene scene = new Scene(view);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}