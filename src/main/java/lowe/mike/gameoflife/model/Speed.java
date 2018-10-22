package lowe.mike.gameoflife.model;

/**
 * The {@code Speed} enum is used to dictate amount of time between each generation in
 * <i>The Game of Life</i>.
 *
 * @author Mike Lowe
 */
public enum Speed {

  SLOW(800), MEDIUM(200), FAST(80), FASTEST(10);

  private final double milliseconds;

  Speed(double milliseconds) {
    this.milliseconds = milliseconds;
  }

  /**
   * Returns the amount of time between each generation in milliseconds.
   *
   * @return the amount of time between each generation in milliseconds.
   */
  public double getMilliseconds() {
    return milliseconds;
  }

}
