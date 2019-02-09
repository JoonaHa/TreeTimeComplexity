## Testing documentation ##

### Junit tests ###
 In it's current state heaps public methods are tested by comparing it to Java's implementation of PriorityQue; which is a min-heap
 and works well for assuring proper functionality.
 The heaps are usually given 1000 pseudorandom integers ranging from 0 to 2147483647. Negative values are used for testing *pop* and *peek* behaviour.
 
 Pop and peek methods are tested by adding integers and removing all of them using *pop* and comparing the returned value to PriorityQue's
 poll-method. Decrease-key method's testing methodology is very similar but instead of removing keys with pop, decrease-key is called for every node in the heap
 and the heap's state is compared to PirorityQue using *peek*
 
