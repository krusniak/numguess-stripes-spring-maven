package numguess.model;

/**
 * An interface for the data shared at the application level.
 */
public interface SharedData {

	boolean setIfBestScore(int bestScore);

	int getBestScore();
}