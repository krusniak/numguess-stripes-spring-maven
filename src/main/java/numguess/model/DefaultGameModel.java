package numguess.model;

import java.util.Random;

/**
 * A class representing the business logic of the game. There is one instance of
 * this class per user session.
 */
public class DefaultGameModel implements GameModel, SharedDataAware {

	private int min, max;

	public synchronized int getMin() {
		return min;
	}

	public synchronized void setMin(final int min) {
		this.min = min;
	}

	public synchronized int getMax() {
		return max;
	}

	public synchronized void setMax(final int max) {
		this.max = max;
	}

	private int answer = -1;

	private int numGuesses = 0;

	private SharedData sharedData;

	public synchronized void setSharedData(final SharedData sharedData) {
		this.sharedData = sharedData;
	}

	public synchronized GuessResult guess(final int guess) {
		boolean isNewBestScore = false;
		int bestScore = Integer.MAX_VALUE;
		// update data model
		numGuesses++;
		// update best score in shared data model if available
		if (guess == answer && sharedData != null) {
			isNewBestScore = sharedData.setIfBestScore(numGuesses);
			bestScore = sharedData.getBestScore();
		}
		// populate resulting ValueObject
		GuessResult result = new GuessResult();
		result.setGuess(guess);
		result.setNumGuesses(numGuesses);
		result.setComparison(guess - answer);
		result.setBestScore(bestScore);
		result.setNewBestScore(isNewBestScore);
		return result;
	}

	public synchronized void reset() {
		answer = min + Math.abs(new Random().nextInt() % (max - min + 1));
		numGuesses = 0;
		System.out.println("The answer is " + answer);
	}
}