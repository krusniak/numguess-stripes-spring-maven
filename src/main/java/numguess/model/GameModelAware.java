package numguess.model;


/**
 * An interface for components that depend on a game model instance.
 */
public interface GameModelAware {

	/**
	 * Injects this object's dependency on the game model.
	 *
	 * @param model
	 * 		the game model
	 */
	void setGameModel(GameModel model);
}
