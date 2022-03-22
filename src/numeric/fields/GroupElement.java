package numeric.fields;

/**
 * An interface representing a group element.
 *
 * @param <T> Used as a covariant type.
 */
public interface GroupElement<T> {
	/**
	 * Multiplies this element with another group element and returns a new
	 * group element representing the result.
	 * 
	 * @param u The group element to multiply with 
	 * @return A singleton group element representing this * u 
	 */
	T times(T u);
	
	/**
	 * An alias for {@link times}
	 */
	T multiply(T u);
	
	/**
	 * Returns the inverse of this group element.
	 */
	T inverse();
		
	/**
	 * Returns the group identity for the group that this element belongs to.
	 */
	T identity();
}
