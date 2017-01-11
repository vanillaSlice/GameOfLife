package lowe.mike.gameoflife.model;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.animation.Animation.Status.RUNNING;
import static lowe.mike.gameoflife.model.Speed.SLOW;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

/**
 * {@code GameOfLife} instances run <i>The Game of Life</i>.
 * 
 * @author Mike Lowe
 */
public final class GameOfLife {

	private final Grid grid;
	private final ReadOnlyLongWrapper generation = new ReadOnlyLongWrapper();
	private Timeline timeline;
	private final ObjectProperty<Speed> speed = new SimpleObjectProperty<>(SLOW);

	/**
	 * Creates a new {@code GameOfLife} instance given the number of rows and
	 * columns to give the {@link Grid}.
	 * 
	 * @param numberOfRows
	 *            the number of rows
	 * @param numberOfColumns
	 *            the number of columns
	 */
	public GameOfLife(int numberOfRows, int numberOfColumns) {
		this.grid = new Grid(numberOfRows, numberOfColumns);
		updateTimeline();
		addSpeedPropertyListener();
	}

	private void updateTimeline() {
		Duration duration = new Duration(getSpeed().getMilliseconds());
		EventHandler<ActionEvent> eventHandler = getEventHandler();
		KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(INDEFINITE);
	}

	private EventHandler<ActionEvent> getEventHandler() {
		return event -> next();
	}

	/**
	 * Transitions into the next generation.
	 */
	void next() {
		grid.nextGeneration();
		generation.set(getGeneration() + 1);
	}

	private void addSpeedPropertyListener() {
		speed.addListener((observable, oldValue, newValue) -> {
			boolean shouldPlay = timeline.getStatus() == RUNNING;
			pause();
			updateTimeline();
			if (shouldPlay)
				play();
		});
	}

	/**
	 * @return the current generation
	 */
	public long getGeneration() {
		return generation.get();
	}

	/**
	 * @return the generation {@link ReadOnlyLongProperty}
	 */
	public ReadOnlyLongProperty generationProperty() {
		return generation.getReadOnlyProperty();
	}

	/**
	 * @return the {@link Grid}
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * @return the {@link Speed} of the game
	 */
	public Speed getSpeed() {
		return speed.get();
	}

	/**
	 * Sets the {@link Speed} of the game.
	 * 
	 * @param speed
	 *            the {@link Speed} of the game
	 */
	public void setSpeed(Speed speed) {
		this.speed.set(speed);
	}

	/**
	 * @return the {@link Speed} {@link ObjectProperty} of the game
	 */
	public ObjectProperty<Speed> speedProperty() {
		return speed;
	}

	/**
	 * Plays the game.
	 */
	public void play() {
		timeline.play();
	}

	/**
	 * Pauses the game.
	 */
	public void pause() {
		timeline.pause();
	}

	/**
	 * Clears the current game.
	 */
	public void clear() {
		pause();
		grid.clear();
		generation.set(0);
	}

	@Override
	public String toString() {
		return "[generation=" + getGeneration() + ", grid=" + grid + "]";
	}

}