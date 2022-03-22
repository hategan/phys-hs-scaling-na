package numeric.exp.na;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import numeric.fields.DiscreteGroupElement;
import numeric.fields.nonabelian.D3;
import numeric.fields.nonabelian.D4;
import numeric.fields.nonabelian.D5;
import numeric.fields.nonabelian.Q8;

public class HSScaling<G extends DiscreteGroupElement<G>> {
	public static final int MAX_PLAQS = 9;
	
	/**
	 * This class stores unphysical basis vectors on the lattice. The 
	 * lattice is a two dimensional 1 x nplaq with maximal tree gauge
	 * fixing. That is, all but nplaq links are fixed to the identity.
	 */
	private static class Lattice<G extends DiscreteGroupElement<G>> implements Comparable<Lattice<G>> {
		private byte[] links; // only store the index of the group element
		private int cachedHashCode;
		private final G group;
		
		@SuppressWarnings("unchecked")
		public Lattice(int nplaq, G group) {
			links = new byte[nplaq];
			this.group = group;
		}

		@Override
		public int hashCode() {
			return cachedHashCode;
		}
				
		/**
		 * Returns <code>true</code> if obj is a lattice with the same 
		 * configuration as this lattice and <code>false</code> otherwise.
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Lattice) {
				@SuppressWarnings("unchecked")
				Lattice<G> p = (Lattice<G>) obj;
				updateHashCode();
				return Arrays.equals(links, p.links);
			}
			else {
				return false;
			}
		}
		
		/**
		 * Returns the group element at a certain plaquette index.
		 */
		@SuppressWarnings("unchecked")
		public G get(int index) {
			return (G) group.all()[links[index]];
		}

		/**
		 * Sets the group element at a certain plaquette index.
		 */
		public void set(int index, G val) {
			links[index] = (byte) val.index();
			updateHashCode();
		}
		
		private void updateHashCode() {
			cachedHashCode = Arrays.hashCode(links);
		}
		
		/**
		 * Creates a copy of this lattice. The new lattice will initially 
		 * have the same configuration as this lattice. However, it is a 
		 * deep copy, in the sense that changing the configuration of this
		 * lattice does not change the configuration of the copy.
		 */
		public Lattice<G> copy() {
			Lattice<G> np = new Lattice<>(links.length, group);
			System.arraycopy(links, 0, np.links, 0, links.length);
			np.cachedHashCode = cachedHashCode;
			return np;
		}

		@Override
		public int compareTo(Lattice<G> o) {
			for (int i = 0; i < links.length; i++) {
				int d = links[i] - o.links[i];
				if (d != 0) {
					return d;
				}
			}
			return 0;
		}
	}

	/**
	 * A set storing configurations representative for an orbit.
	 * That is, the set will never contain two configurations in 
	 * the same orbit.
	 */
	private Set<Lattice<G>> orbits;
	private int steps;
	private int ncc; // number of conjugacy classes
	private int nplaq;
	
	/**
	 * A gauge transformation table that pre-computes G[g, h] = h g h^(-1).
	 */
	private G[][] gts;
	private G group;
	
	@SuppressWarnings("unchecked")
	public HSScaling(G group) {
		this.group = group;
		gts = (G[][]) new DiscreteGroupElement[group.all().length][group.all().length];
	}
	
	public void run() {
		// Fill the gauge transformation table
		
		
		for (G g: group.all()) {
			for (G s: group.all()) {
				gts[g.index()][s.index()] = g.times(s).times(g.inverse());
			}
		}
		// Run the main code for lattice sizes from 1x1 to 1x9.
		// For larger sizes, things become too slow and the benefit is marginal. 
		for (int i = 1; i <= MAX_PLAQS; i++) {
			nplaq = i;
			run2();
		}
	}
	
	public static final NumberFormat NF = new DecimalFormat("000000000000");
	
	public void run2() {
		orbits = new HashSet<>();
		Lattice<G> p = new Lattice<>(nplaq, group);
		gen(0, p);
		
		int dimHSphys = orbits.size();
		if (nplaq == 1) {
			ncc = dimHSphys;
		}
		int dimHSabel = pow(ncc, nplaq);
		int dimHSunphys = pow(group.all().length, nplaq);
		System.out.println(String.format("%7d%15d%17d%15d", 
				nplaq, dimHSphys, dimHSunphys, dimHSabel));		
	}
		
	private int pow(int a, int n) {
		int r = 1;
		for (int i = 0; i < n; i++) {
			r = r * a;
		}
		return r;
	}

	/**
	 * Generate and check configurations. The configurations are generated
	 * recursively and the check is done as part of the stopping condition.
	 */
	private void gen(int i, Lattice<G> p) {
		if (i == nplaq) {
			check(p);
			steps++;
		}
		else {
			// generate state
			for (G l : group.all()) {
				p.set(i, l);
				gen(i + 1, p);
			}
		}
	}

	/**
	 * Check if a configuration is related to an existing gauge orbit
	 * by a transformation and, if not, add it to the list of orbits.  
	 */
	private boolean check(Lattice<G> p) {
		for (G l : group.all()) {
			if (checkgt(p, l)) {
				// no need to check the rest
				return true;
			}
			else {
				// continue checking
			}
		}
		orbits.add(p.copy());
		return false;
	}

	/**
	 * Check if the given lattice, when transformed by a global gauge
	 * trasnformation with <code>g</code>, is stored in the list of 
	 * seen orbits. 
	 */
	private boolean checkgt(Lattice<G> p, G g) {
		Lattice<G> q = new Lattice<>(nplaq, group);
		// optimize a bit by retrieving the multiplication column for g
		G[] gt = gts[g.index()];
		for (int i = 0; i < nplaq; i++) {
			q.set(i, transform(p.get(i), gt));
		}
		
		if (orbits.contains(q)) {
			return true;
		}
		else {
			return false;
		}
	}

	private G transform(G g, G[] gt) {
		return gt[g.index()];
	}
	
	private static void header(String groupName) {
		System.out.println("\n");
		System.out.println("*********************************");
		System.out.println("**        Group " + groupName + "             **");
		System.out.println("*********************************");
		System.out.println();
		System.out.println("Lat. sz.   dim(HS_phys)   dim(HS_unphys)   dim(HS_abel)");
		System.out.println("-------------------------------------------------------");
	}

	public static void main(String[] args) {
		try {
			header("Q8");
			new HSScaling<Q8>(Q8.IDENTITY).run();
			header("D3");
			new HSScaling<D3>(D3.IDENTITY).run();
			header("D4");
			new HSScaling<D4>(D4.IDENTITY).run();
			header("D5");
			new HSScaling<D5>(D5.IDENTITY).run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
