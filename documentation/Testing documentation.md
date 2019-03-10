## Testing documentation ##


### Junit tests ###
 In it's current state heaps methods are tested by comparing it to Java's implementation of PriorityQue; which is a min-heap
 and works well for assuring proper functionality.
 The heaps are usually given 1000 pseudorandom integers ranging from 0 to 2147483647. Negative values are used for testing *pop* and *peek* behaviour. 
 Pop and peek methods are tested by adding integers and removing all of them using *pop* and comparing the returned value to PriorityQue's
 poll-method. Decrease-key method's testing methodology is very similar but instead of removing keys with pop, decrease-key is called for every node in the heap and the heap's state is compared to PirorityQue using *peek*.
 Most of development was tested using thes Junit tests. Making sure that different heaps actually maintain heap property.
 
 Same methodology is used for testing GenericArrayList: the implemetation is compared to java's ArrayList. 
 Methods *get*, *set*, *remove* and *getSize* are tested using Junit.
 
 ![](https://github.com/JoonaHa/TreeTimeComplexity/blob/master/documentation/jacoco.png)
 
 ### Manual testing ###
 The GUI was tested manually trying different invalid combinations of values. 
 The heap implementations complexity was compared to each other using JavaFx's barcharts so that the differences between different heaps are logical. Fibonacciheap had a nasty bug that came to light with this method.
 
 
#### Results comparing diffrent heap-operations to Java's PriroityQue ####
Results are averages in nanoseconds with 300 test iterations and random data with 1000 integers.
Given opearation is run for the whole lenght of the input data. Except for peek.


     --------TEST DECREASE KEY--------
    BinaryHeap/PriorityQue: 778434/374833
    BinomialHeap/PriorityQue: 3807745/374833
    FibonacciHeap/PriorityQue: 958329/374833

    --------TEST ADD--------
    BinaryHeap/PriorityQue: 117472/37360
    BinomialHeap/PriorityQue: 395202/37360
    FibonacciHeap/PriorityQue: 23530/37360

    --------TEST POP--------
    BinaryHeap/PriorityQue: 833066/13338
    BinomialHeap/PriorityQue: 4843571/13338
    FibonacciHeap/PriorityQue: 4855696/13338

    --------TEST PEEK--------
    BinaryHeap/PriorityQue: 91/53
    BinomialHeap/PriorityQue: 528/53
    FibonacciHeap/PriorityQue: 63/53

    --------TEST DELETE--------
    BinaryHeap/PriorityQue: 1332490/187565
    BinomialHeap/PriorityQue: 2473915/187565
    FibonacciHeap/PriorityQue: 5025446/187565


The Junit test and the comparisons can be recreated running
On linux:
```
./gradlew test
```
On Windows:
```
gradlew.bat test
```
 Run thes inside TreeTimeComplexity-folder (not root folder).
