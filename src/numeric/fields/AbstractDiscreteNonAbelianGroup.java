package numeric.fields;

import java.lang.reflect.Array;


/**
 * An abstract class implementing boilerplate code for a discrete non-abelian 
 * group, such as multiplication using a table, and an inverse table.
 *
 * @param <T>
 */
public abstract class AbstractDiscreteNonAbelianGroup<T extends AbstractDiscreteNonAbelianGroup<?>> implements DiscreteGroupElement<T>, Comparable<T> {
	@SuppressWarnings("unchecked")
	protected static <T extends AbstractDiscreteNonAbelianGroup<?>> T[][] normalizeTable(T[][] t) {
		T[][] nt = (T[][]) Array.newInstance(t[0].getClass(), t.length - 1);
		
		for (int i = 0; i < nt.length; i++) {
			nt[i] = (T[]) Array.newInstance(t[0][1].getClass(), t.length - 1);
		}
		
		for (int i = 0; i < nt.length; i++) {			
			for (int j = 0; j < nt.length; j++) {
				nt[t[i + 1][0].index()][t[0][j + 1].index()] = t[i + 1][j + 1];
			}
		}
		return nt;
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> T[] buildInverseTable(T[][] t, T identity, T[] all) {
		T[] nt = (T[]) Array.newInstance(t[0][1].getClass(), t.length);
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t.length; j++) {
				if (t[i][j] == identity) {
					nt[i] = all[j];
				}
			}
		}
		return nt;
	}
	
	
	private final int index;
	private final String name;
		
	protected AbstractDiscreteNonAbelianGroup(int index, String name) {
		this.index = index;
		this.name = name;
	}
	
	@Override
	public int index() {
		return index;
	}
	
	protected abstract T[][] getMultiplicationTable();
	
	protected abstract T[] getInverseTable();
	
	public abstract T[] all();

	@Override
	public T times(T u) {
		return getMultiplicationTable()[index][u.index()];
	}

	@Override
	public T multiply(T u) {
		return times(u);
	}

	@Override
	public T inverse() {
		return getInverseTable()[index];
	}
	
	public String toString() {
		return name;
	}
	
	public int hashCode() {
		return index;
	}
	
	@Override
	public int compareTo(T o) {
		return index - o.index();
	}

	public boolean equals(T m) {
		return index == m.index();
	}
	
	public String name() {
		return name;
	}
}
