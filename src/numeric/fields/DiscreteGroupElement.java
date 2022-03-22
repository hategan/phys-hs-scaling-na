package numeric.fields;

/**
 * An interface representing an element of a discrete group.
 *
 * @param <T>
 */
public interface DiscreteGroupElement<T> extends GroupElement<T> {
	/**
	 * Used to associate a mapping from the elements of the group to Z.
	 */
	int index();

	/**
	 * Returns an array with all the elements of the group that this element
	 * belongs to.
	 */
	T[] all();
}
