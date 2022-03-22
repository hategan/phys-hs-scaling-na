package numeric.fields.nonabelian;

import numeric.fields.AbstractDiscreteNonAbelianGroup;
import numeric.fields.GroupElement;

/**
 * A class with group elements belonging to the dihedral group D4.
 *
 */
public class D4 extends AbstractDiscreteNonAbelianGroup<D4> implements Comparable<D4>, GroupElement<D4> {
	public static final D4 E = new D4(0, "  E");
	public static final D4 IDENTITY = E;
	
	public static final D4 A = new D4(1, "  A");
	public static final D4 A2 = new D4(2, " A2");
	public static final D4 A3 = new D4(3, " A3");
	public static final D4 X = new D4(4, "  X");
	public static final D4 AX = new D4(5, " AX");
	public static final D4 A2X = new D4(6, "A2X");
	public static final D4 A3X = new D4(7, "A3X");
	
	public static final D4[] ALL = new D4[] {E, A, A2, A3, X, AX, A2X, A3X};
	public static final D4[][] MTABLE = normalizeTable(new D4[][]
			{
				{null,   E,   A,  A2,  A3,   X,  AX, A2X, A3X},
				{   E,   E,   A,  A2,  A3,   X,  AX, A2X, A3X},
				{   A,   A,  A2,  A3,   E,  AX, A2X, A3X,   X},
				{  A2,  A2,  A3,   E,   A, A2X, A3X,   X,  AX},
				{  A3,  A3,   E,   A,  A2, A3X,   X,  AX, A2X},
				{   X,   X, A3X, A2X,  AX,   E,  A3,  A2,   A},
				{  AX,  AX,   X, A3X, A2X,   A,   E,  A3,  A2},
				{ A2X, A2X,  AX,   X, A3X,  A2,   A,   E,  A3},
				{ A3X, A3X, A2X,  AX,   X,  A3,  A2,   A,   E},
			}
	);

	public static final D4[] INVTABLE = buildInverseTable(MTABLE, IDENTITY, ALL);
			
	public D4(int index, String name) {
		super(index, name);
	}

	@Override
	protected D4[][] getMultiplicationTable() {
		return MTABLE;
	}

	@Override
	protected D4[] getInverseTable() {
		return INVTABLE;
	}

	@Override
	public D4[] all() {
		return ALL;
	}

	@Override
	public D4 identity() {
		return E;
	}
}
