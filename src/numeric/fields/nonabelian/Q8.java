package numeric.fields.nonabelian;

import numeric.fields.AbstractDiscreteNonAbelianGroup;
import numeric.fields.GroupElement;


/**
 * A class with group elements belonging to quaternion group.
 *
 */
public class Q8 extends AbstractDiscreteNonAbelianGroup<Q8> implements Comparable<Q8>, GroupElement<Q8> {
	public static final Q8 I = new Q8(0, " 1");
	public static final Q8 IDENTITY = I;
	
	public static final Q8 S1 = new Q8(1, "S1");
	public static final Q8 S2 = new Q8(2, "S2");
	public static final Q8 S3 = new Q8(3, "S3");
	public static final Q8  J = new Q8(4, " J");
	public static final Q8 MS1 = new Q8(5, "T1");
	public static final Q8 MS2 = new Q8(6, "T2");
	public static final Q8 MS3 = new Q8(7, "T3");
	
	public static final Q8[] ALL = new Q8[] {I, S1, S2, S3, J, MS1, MS2, MS3};
	public static final Q8[][] MTABLE = normalizeTable(new Q8[][]
			{
				{null,   I,   J,  S1, MS1,  S2, MS2,  S3, MS3},
				{   I,   I,   J,  S1, MS1,  S2, MS2,  S3, MS3},
				{   J,   J,   I, MS1,  S1, MS2,  S2, MS3,  S3},
				{  S1,  S1, MS1,   J,   I,  S3, MS3, MS2,  S2},
				{ MS1, MS1,  S1,   I,   J, MS3,  S3,  S2, MS2},
				{  S2,  S2, MS2, MS3,  S3,   J,   I,  S1, MS1},
				{ MS2, MS2,  S2,  S3, MS3,   I,   J, MS1,  S1},
				{  S3,  S3, MS3,  S2, MS2, MS1,  S1,   J,   I},
				{ MS3, MS3,  S3, MS2,  S2,  S1, MS1,   I,   J},
			}
	);

	public static final Q8[] INVTABLE = buildInverseTable(MTABLE, IDENTITY, ALL);
			
	public Q8(int index, String name) {
		super(index, name);
	}

	@Override
	protected Q8[][] getMultiplicationTable() {
		return MTABLE;
	}

	@Override
	protected Q8[] getInverseTable() {
		return INVTABLE;
	}

	@Override
	public Q8[] all() {
		return ALL;
	}
		
	@Override
	public Q8 identity() {
		return I;
	}
}
