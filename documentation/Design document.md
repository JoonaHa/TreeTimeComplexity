## Design document ##

TreeTimeComplexity consists of three types of heaps that are compared to each other with sorted and unsorted integer lists functioining as test data.
They inherit the same abstract class *Heaps* which is used to build the desired benchmark and insures that all of the heaps contain the same operations tested in the benchmark phase.


## Algorithms and complexity ##
For the software's basic functionality some lists and arrays are needed and currently *MinBinomialHeap* uses Java implementation of doubly linked list
but it will be replaced by pointers in the *BinomialTreeNodes*.

