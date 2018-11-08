package lowe.mike.gameoflife.model;

/**
 * {@code Utils} contains useful helper methods.
 *
 * <p>Instances of {@code Utils} cannot be created.
 *
 * @author Mike Lowe
 */
class Utils {

  // don't want instances
  private Utils() {
  }

  /**
   * Checks if a number is greater than 0, if not then an {@link IllegalArgumentException} is
   * thrown.
   *
   * @param number the number to check
   * @param message detail message to be used in the event that an {@link
   *     IllegalArgumentException} is thrown
   * @return {@code number}
   */
  static int requirePositiveNumber(int number, String message) {
    if (number <= 0) {
      throw new IllegalArgumentException(message);
    }
    return number;
  }

}
