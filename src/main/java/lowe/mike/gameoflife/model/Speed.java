package lowe.mike.gameoflife.model;

/**
 * The {@code Speed} enum is used to dictate the amount of time between each generation in
 * <i>The Game of Life</i>.
 *
 * @author Mike Lowe
 */
public enum Speed {

  /**
   * Slow - 800 milliseconds between generations.
   */
  SLOW(800),

  /**
   * Medium - 200 milliseconds between generations.
   */
  MEDIUM(200),

  /**
   * Fast - 80 milliseconds between generations.
   */
  FAST(80),

  /**
   * Fastest - 10 milliseconds between generations.
   */
  FASTEST(10);

  private final double milliseconds;

  Speed(double milliseconds) {
    this.milliseconds = milliseconds;
  }

  /**
   * Returns the amount of time between each generation in milliseconds.
   *
   * @return the amount of time between each generation in milliseconds
   */
  public double getMilliseconds() {
    return milliseconds;
  }

}
