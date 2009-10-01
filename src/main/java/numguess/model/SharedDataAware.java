package numguess.model;


/**
 * An interface for components that depend on the shared data.
 */
public interface SharedDataAware {

	/**
	 * Injects this object's dependency on the shared data.
	 *
	 * @param sharedData
	 *            the shared data
	 */
	void setSharedData(SharedData sharedData);
}
