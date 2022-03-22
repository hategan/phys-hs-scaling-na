This repository contains code which calculates the number of physical
states in small gauge lattices with discrete non-abelian groups.

## Description

The main program is numeric.exp.na.HSScaling. It sets up a maximal tree
gauge fixed lattice and generates all possible configurations of group
elements on the unfixed links. It then generates all possible global
gauge transformations, and transforms the initial configuration. If at
some point a configuration that has been seen before is reached, it 
moves to the next initial configuration. If none of the gauge transformed
configurations has been seen before, it counts the initial configuration
as a physical state.

The process is repeated for lattice sizes of the form 1 x n, with n being
in the range {1...9}. This range is mostly limited by available memory,
since seen physical configurations must be stored in memory and the
number of configurations tends to dim(G)^n, where dim(G) is the dimension
of the group.

The process is then repeated for a number of groups, including Q8 and the
dihedrals D3-D5.

The results are printed in a table with the following columns:

1. The size of the lattice (n)

2. dim(HS_phys) - the dimension of the physical Hilbert space

3. dim(HS_unphys) - the dimenion of the unphysical Hilbert space

4. dim(HS_abel) - for comparison purposes, the dimension of the physical
space of a theory with an abelian group of the same dimension as the
dimension of dim(HS_phys) for a single plaquette lattice.

## Usage

You need a recent version of Java and Apache Ant and a unix-like
environment, although this could be made to work in Windows with some
effort.

To compile the code, run `ant jar` in the main directory. This should
produce `lib/phys-hs-scaling-na.jar` (although a pre-compiled version of
the jar file should be present in the repository).

To run the main program:

```bash
cd bin
./hs-scaling
```
