package algorithms;

import java.util.ArrayList;

/**
 * Min Binary Heap which extends abstract class Heaps
 *
 * @author JoonaHa
 */
public class MinBinaryHeap extends Heaps {

    public MinBinaryHeap() {
        super();

    }

    public MinBinaryHeap(ArrayList<Integer> list) {
        super(list);

        createHeap();
    }

    /**
     * Inserts a value to the heap
     *
     * @param insert
     */
    @Override
    public void add(int insert) {

        // add node to the end of the list and get the last index
        this.heap.add(insert);
        int last = this.size() - 1;
        int index = last;

        //if the inserted node isn't the first note 
        //siftup to keep the heap on order
        if (last != 0) {

            siftUp(index);
        }

    }

    /**
     * Delete a node from given index and keep the heap in order
     *
     * @param index of a node to be removed
     */
    @Override
    public int delete(int index) {
        
        int value;
        // if index is root use  pop
        if (index == 0) {
            value = heap.get(index);
            pop();

            // else change the nodes value to -INF sort the heap and use pop to remove it    
        } else {
            value = heap.get(index);
            int min = Integer.MIN_VALUE;
            this.heap.set(index, min);

            siftUp(index);

            pop();

        }

        return value;
    }

    /**
     * Removes the node with the smallest value and returns it.
     *
     * @return the smallest node of the heap
     */
    @Override
    public int pop() {

        // save the root value
        int rootValue = this.heap.get(0);

        int last = this.size() - 1;
        //check if the root is the only node
        // else swap the root to the end of the heap, remove it
        if (last != 0) {

            super.swap(0, last);

            this.heap.remove(last);

            //sift the new root down to keep the heap sorted 
            siftDown(0);

        }

        return rootValue;

    }

    /**
     * Sift down a node from the given index to it's proper level to sustain
     * heap property
     *
     * @param index where to start the sifting
     */
    @Override
    protected void siftDown(int index) {

        int last = this.size() - 1;
        int value = this.heap.get(index);

        //swap the given node with one of its children downwards 
        //till it's childrens have a greater value or the node is a leaf
        while (leftChildIndex(index) <= last) {

            int left = leftChild(index);
            int right = rightChild(index);

            if (left < value && left < right) {
                swap(leftChildIndex(index), index);
                index = leftChildIndex(index);
            } else if (right < value) {
                swap(rightChildIndex(index), index);
                index = rightChildIndex(index);

            } else {
                break;
            }
        }
    }

    /**
     * Sift up a node from the given index to it's proper level to sustain heap
     * property
     *
     * @param index where to start the sifting
     */
    @Override
    protected void siftUp(int index) {

        int value = this.heap.get(index);
        int parent = this.parent(index);

        //swap node with its parent till its parent is smaller 
        //or the node is root
        while (index >= 0 && parent > value) {

            swap(parentIndex(index), index);

            index = parentIndex(index);
            parent = this.parent(index);

        }
    }

    private void createHeap() {
        for (int i = parentIndex(size()); i >= 0; i--) {

            siftDown(i);

        }

    }

}
