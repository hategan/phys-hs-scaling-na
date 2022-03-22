package numeric.fields.nonabelian;

import numeric.fields.AbstractDiscreteNonAbelianGroup;
import numeric.fields.GroupElement;


/**
 * A class with group elements belonging to the dihedral group D5.
 *
 */
public class D5 extends AbstractDiscreteNonAbelianGroup<D5> implements Comparable<D5>, GroupElement<D5> {
	public static final D5 R0 = new D5(0, "R0");
	public static final D5 IDENTITY = R0;
	
	public static final D5 R1 = new D5(1, "R1");
	public static final D5 R2 = new D5(2, "R2");
	public static final D5 R3 = new D5(3, "R3");
	public static final D5 R4 = new D5(4, "R4");
	public static final D5 F1 = new D5(5, "F1");
	public static final D5 F2 = new D5(6, "F2");
	public static final D5 F3 = new D5(7, "F3");
	public static final D5 F4 = new D5(7, "F4");
	public static final D5 F5 = new D5(7, "F5");
	
	public static final D5[] ALL = new D5[] {R0, R1, R2, R3, R4, F1, F2, F3, F4, F5};
	public static final D5[][] MTABLE = normalizeTable(new D5[][]
			{
				{null,  R0,  R1,  R2,  R3,  R4,  F1,  F2,  F3,  F4,  F5},
				{  R0,  R0,  R1,  R2,  R3,  R4,  F1,  F2,  F3,  F4,  F5},
				{  R1,  R1,  R2,  R3,  R4,  R0,  F4,  F5,  F1,  F2,  F3},
				{  R2,  R2,  R3,  R4,  R0,  R1,  F2,  F3,  F4,  F5,  F1},
				{  R3,  R3,  R4,  R0,  R1,  R2,  F5,  F1,  F2,  F3,  F4},
				{  R4,  R4,  R0,  R1,  R2,  R3,  F3,  F4,  F5,  F1,  F2},
				{  F1,  F1,  F3,  F5,  F2,  F4,  R0,  R3,  R1,  R4,  R2},
				{  F2,  F2,  F4,  F1,  F3,  F5,  R2,  R0,  R3,  R1,  R4},
				{  F3,  F3,  F5,  F2,  F4,  F1,  R4,  R2,  R0,  R3,  R1},
				{  F4,  F4,  F1,  F3,  F5,  F2,  R1,  R4,  R2,  R0,  R3},
				{  F5,  F5,  F2,  F4,  F1,  F3,  R3,  R1,  R4,  R2,  R0},
			}
	);

	public static final D5[] INVTABLE = buildInverseTable(MTABLE, IDENTITY, ALL);
			
	public D5(int index, String name) {
		super(index, name);
	}

	@Override
	protected D5[][] getMultiplicationTable() {
		return MTABLE;
	}

	@Override
	protected D5[] getInverseTable() {
		return INVTABLE;
	}

	@Override
	public D5[] all() {
		return ALL;
	}

	@Override
	public D5 identity() {
		return R0;
	}
}
