package numeric.fields.nonabelian;

import numeric.fields.AbstractDiscreteNonAbelianGroup;
import numeric.fields.GroupElement;


/**
 * A class with group elements belonging to the dihedral group D3.
 *
 */
public class D3 extends AbstractDiscreteNonAbelianGroup<D3> implements Comparable<D3>, GroupElement<D3> {
	public static final D3 R0 = new D3(0, "R0");
	public static final D3 IDENTITY = R0;
	
	public static final D3 R1 = new D3(1, "R1");
	public static final D3 R2 = new D3(2, "R2");
	public static final D3 S0 = new D3(3, "S0");
	public static final D3 S1 = new D3(4, "S1");
	public static final D3 S2 = new D3(5, "S2");
	
	public static final D3[] ALL = new D3[] {R0, R1, R2, S0, S1, S2};
	public static final D3[][] MTABLE = normalizeTable(new D3[][]
			{
				{null,  R0,  R1,  R2,  S0,  S1,  S2},
				{  R0,  R0,  R1,  R2,  S0,  S1,  S2},
				{  R1,  R1,  R2,  R0,  S1,  S2,  S0},
				{  R2,  R2,  R0,  R1,  S2,  S0,  S1},
				{  S0,  S0,  S2,  S1,  R0,  R2,  R1},
				{  S1,  S1,  S0,  S2,  R1,  R0,  R2},
				{  S2,  S2,  S1,  S0,  R2,  R1,  R0},
			}
	);

	public static final D3[] INVTABLE = buildInverseTable(MTABLE, IDENTITY, ALL);
			
	public D3(int index, String name) {
		super(index, name);
	}

	@Override
	protected D3[][] getMultiplicationTable() {
		return MTABLE;
	}

	@Override
	protected D3[] getInverseTable() {
		return INVTABLE;
	}

	@Override
	public D3[] all() {
		return ALL;
	}
		
	@Override
	public D3 identity() {
		return R0;
	}
}
