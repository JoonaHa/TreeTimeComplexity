package algorithms;

import java.util.ArrayList;

/**
 * Min Binary Heap which extends abstract class Heaps
 *
 * @author JoonaHa
 */
public class MinBinaryHeap extends Heaps {

    private ArrayList<Integer> heap;

    /**
     * Create a empty dynamically sized heap
     */
    public MinBinaryHeap() {
        this.heap = new ArrayList<Integer>();

    }

    /**
     * Create a Heap from a existing list.
     *
     * @param list Existing list that is changed to a heap.
     */
    public MinBinaryHeap(ArrayList<Integer> list) {
        this.heap = list;

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
        int last = this.getSize() - 1;
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
     * @param value of a node to be removed
     */
    @Override
    public int delete(int value) {

        int index = findIndex(value);
        // if index is root use  pop
        if (index == 0) {
            pop();

            // else change the nodes value to -INF sort the heap and use pop to remove it    
        } else {
            int min = Integer.MIN_VALUE;
            this.heap.set(index, min);

            siftUp(index);

            pop();

        }

        return value;
    }

    @Override
    public int peek() {
        return this.heap.get(0);
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

        int last = this.getSize() - 1;
        //check if the root is the only node
        // else swap the root to the end of the heap, remove it
        if (last != 0) {

            swap(0, last);

            this.heap.remove(last);

            //sift the new root down to keep the heap sorted 
            siftDown(0);

        }

        return rootValue;

    }

    @Override
    public int decreaseKey(int value) {

        // Increment old value by one and update the node
        int index = findIndex(value);
        int oldValue = this.heap.get(index);
        this.heap.set(index, oldValue - 1);

        // if node's parents value is greater siftUp to keep heap property
        if (parent(index) > oldValue - 1) {
            siftUp(index);
        }
        return oldValue;

    }

    /**
     * Tells the getSize of a heap
     *
     * @return Integer describing a heap's current getSize
     */
    public int getSize() {
        return this.heap.size();
    }

    @Override
    public void clear() {
        this.heap = new ArrayList<Integer>();

    }

    /**
     * Sift down a node from the given index to it's proper level to sustain
     * heap property
     *
     * @param index where to start the sifting
     */
    private void siftDown(int index) {

        int last = this.getSize() - 1;
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
    private void siftUp(int index) {

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
        for (int i = parentIndex(getSize()); i >= 0; i--) {

            siftDown(i);

        }

    }

    private int parent(int index) {
        return this.heap.get((parentIndex(index)));
    }

    /**
     * Takes a node's index and returns its left child's key. If the node
     * doesn't have a child returns the node's own value
     *
     * @param index of a given node in the heap
     * @return Node's left child's key
     */
    private int leftChild(int index) {

        if (leftChildIndex(index) > this.heap.size() - 1) {

            return this.heap.get(index);
        }

        return this.heap.get(leftChildIndex(index));
    }

    /**
     * Takes a node's index and returns its right child's key. If the node
     * doesn't have a child returns the node's own value
     *
     * @param index of agiven node
     * @return Node's right childe's key
     */
    private int rightChild(int index) {

        if (rightChildIndex(index) > this.heap.size() - 1) {

            return this.heap.get(index);
        }

        return this.heap.get(rightChildIndex(index));
    }

    /**
     * Takes to indexes and swaps their key values
     *
     * @param index_a firts index to swap to
     * @param index_b second index to swap from
     */
    private void swap(int index_a, int index_b) {

        int temporary = this.heap.get(index_a);

        this.heap.set(index_a, this.heap.get(index_b));

        this.heap.set(index_b, temporary);

    }

    /**
     * Calculates the parent index of a node If the index is root, return
     * itself.
     *
     * @param index index of a given node
     * @return the node's parent index
     */
    private int parentIndex(int index) {

        if (index == 0) {
            return 0;
        }

        return (index - 1) / 2;
    }

    /**
     * Calculates the left child of a node. Can make null references.Check the
     * index before using.
     *
     * @param index index of a given node
     * @return the node's left child
     */
    private int leftChildIndex(int index) {

        return 2 * index + 1;

    }

    /**
     * Calculates the right child of a node. Can make null references.Check the
     * index before using.
     *
     * @param index index of a given node
     * @return the node's right child
     */
    private int rightChildIndex(int index) {

        return 2 * index + 2;
    }

    public int findIndex(int value) {

        for (int i = 0; i < this.heap.size(); i++) {

            if (this.heap.get(i) == value) {
                return i;
            }
        }

        return -1;
    }

}
