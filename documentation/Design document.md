# Implementation

### Basic Structure
The heart of the project is interface MinHeaps and classes that implement it; those  being *MinBinaryHeap*,  *MinBinomialHeap* and *MinFibonaciHeap*. The interface insures that all heaps support the same basic heap operations and essential utility methods like *clear()* *and getSize()*
In the same alghoritms package, are  classes called *GenericArrayList*, *IntQuickSort*. 
*GenericArrayList* is a arraylist implementation for generic types, used for heap input data, BinaryHeap and BinomialHeap's root list. 
*IntQuickSort* includes quicksort implementation for integers for ascending and descending sorts.

Program's main menu is located on the *App* class. The  menu asks for user to input array size for testing, it's sorting (random/ascending/descending) , heap-operations to benchmark and how many iterations these test should run. Based on these parameters a instance of *BenchmarkResultGUI* is created which uses *utils/Benchmark* class to do the actual testing for by calling method Benchmark.test<OPERATOR>(). The given method returns an array containing min, max and average running times in nanoseconds. These results are displayed in a window returned by *BenchmarkResultGUI* .
The two enum types are used for passing selected sorting and benchmark operations to the Benchmark class.

### Algorithms, data structures and their complexity ###
The programs now has implementationf of **Binaryheap, Binomialheap, Fibonacciheap,  Quicksort, Arraylist**, **Countingsort** for Binomialtrees and simple **pseudorandom number generato**r, based on System.nanotime, for generating benchmarking data.

#### Heap Time Complexities ####

For **Binaryheap** most of the desired complexities were achieved except for decrease and delete.
For practical reasons decrease and delete takes nodes value as a parameter and the whole heap has to be searched. Turning the
opertations to linear time in the worst case.

| Heap:       |  peek (return root)    | pop (delete root)| add | decrease key | delete |
| ------------|:----------------------:|:----------------:|:------:|:------------:|:-------:|
| Binary       |  O(1)                 | O(log n)       | O(log n)   | O(n) | O(n) |


For **Binomialheap** the same thing applies for decrease and delete turning logarmic time to linear. And if the given node is not found from the root list the finNode(value) method recusively goes trough the Binomial trees.

| Heap:       |  peek (return root)    | pop (delete root)| add | decrease key | delete |
| ------------|:----------------------:|:----------------:|:------:|:------------:|:-------:|
| Binomial       |  O(log n)                 | O(log n)       | O(1) (amortized)   | O(n) | O(n) |

For **Fibonacciheap** because searching for node is again a linear timed operation even the amortized constant time operation decrease key is turned in to linear timed one. 

| Heap:       |  peek (return root)    | pop (delete root)| add | decrease key | delete |
| ------------|:----------------------:|:----------------:|:------:|:------------:|:-------:|
| Fibonacci       |  O(1)                 | O(log n)       | O(1)  | O(n) | O(n) |

**Note!** that *Binomialheap* *Binaryheap*  have some overhead because both of them use an implementation of arraylist for dynamic sizing, while *Fibonacciheap* uses doubly linked list.


![](https://github.com/JoonaHa/TreeTimeComplexity/tree/master/documentation/input4000.png)
![](https://github.com/JoonaHa/TreeTimeComplexity/tree/master/documentation/input60.png)

For large (n=4000) or small (n=60) input data Fibonacciheap comes on top with it's decrease key operation.

Also Binomialheap's O(1) amortized add has now match for the real O(1) add of Fibonacciheap. Intrestingly Binaryheap in practice also is consistently faster in add than BinomialHeap. This is propably because Binomialheap's union-operation used in add is basicly O(4(log n)) in my current implementation compared to binary heap's actual O(log n).

![](https://github.com/JoonaHa/TreeTimeComplexity/tree/master/documentation/input60.png)

Binomialheap and Fibonacciheap change top places for pop and delete when heap size is increased even slightly (n=100).
As metioned before Binomialheap's union-operation is fairly slow for O(log n) which might put it in a disadvantage with smaller data but both of them use O(n) operation findNode() and Binomialheap implementation is slightly better (only recursing to trees if needed) while FibonacciHeap goes trough the whole linked-list. 

#### Possible improvements:
* Implement merge opration to MinHeaps and make Benchmark supprot it
* Use linkedlist in BinomialHeap roots instead of arraylist
* Get rid of countingsort in BinomialHeap
* Currently heaps might as well be fixed size to remove the overhead of arraylist


#### Sources:
* https://github.com/nlfiedler/graphmaker/blob/master/core/src/com/bluemarsh/graphmaker/core/util/FibonacciHeap.java ,
* https://www.geeksforgeeks.org/binomial-heap-2/ ,
* CORMEN, THOMAS H. (2009). INTRODUCTION TO ALGORITHMS. United States of America: The MIT Press Cambridge, Massachusetts London, England. 



