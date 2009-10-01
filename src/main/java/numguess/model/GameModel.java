package numguess.model;

/**
 * An interface for the business logic of this web application.
 */
public interface GameModel {

	/**
	 * Submits a guess to this model instance.
	 *
	 * @param guess
	 *            the guess
	 * @return the result of this guess
	 */
	GuessResult guess(int guess);

	/**
	 * Resets this model instance to begin a new game.
	 */
	void reset();

	int getMin();

	int getMax();
}
