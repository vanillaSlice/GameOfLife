package lowe.mike.gameoflife.model;

/**
 * The {@code Speed} enum is used to dictate the speed of <i>The Game of
 * Life</i>.
 *
 * @author Mike Lowe
 */
public enum Speed {

  SLOW(800), MEDIUM(200), FAST(80), FASTEST(10);

  private final double milliseconds;

  Speed(double milliseconds) {
    this.milliseconds = milliseconds;
  }

  double getMilliseconds() {
    return milliseconds;
  }

}
