/*
 * Copyright (C) 2019 JoonaHa
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package algorithms;

import java.util.ArrayList;

/**
 *
 * Abstract class that implemets the basic operations of a heap. Creates a
 * dynamic heap.
 *
 *
 * @author JoonaHa
 */
public abstract class Heaps {

    protected  ArrayList<Integer> heap;

    /**
     * Create a empty dynamically sized heap
     */
    public Heaps() {
        this.heap = new ArrayList<Integer>();
    }

    /**
     * Create a Heap from a existing list. Remember to override in a extending
     * class
     *
     * @param list Existing list that is changed to a heap.
     */
    public Heaps(ArrayList<Integer> list) {
        this.heap = list;

    }

    public int peek() {
        return this.heap.get(0);
    }

    /**
     * Tells if heap is empty.
     *
     * @return A Boolean value, returns true if heap is empty.
     */
    public Boolean isEmpty() {
        if (this.heap.size() != 0) {

            return false;
        }

        return true;
    }

    /**
     * Tells the size of a heap
     *
     * @return Integer describing a heap's current size
     */
    public int size() {
        return this.heap.size();
    }

    /**
     * Abstract method to add nodes. Implementation depends on the heap type.
     *
     * @param insert Value to add to the heap.
     */
    public abstract void add(int insert);

    /**
     * Abstract method to delete a value from a given index. Implementation
     * depends on the heap type.
     *
     * @return removed node's value
     * @param index Index to delete a value from
     */
    public abstract int delete(int index);

    /**
     * Abstract method that returns and delets the root node.
     *
     * @return root node's key value
     */
    public abstract int pop();

    /**
     * Decrease key by 1 form a given index. Return the old value of the key
     *
     * @param index where which value will be incremented
     * @return value of the old key
     */
    public abstract int decreaseKey(int index);

    /**
     * Also somitems callded heapify. Sifts a node down to it's right level to
     * keep the heap property
     *
     * @param index of a node that needs to be shifted
     */
    protected abstract void siftDown(int index);

    /**
     * Sifts a node up to it's right level to keep the heap property
     *
     * @param index of a node that needs to be shifted.
     */
    protected abstract void siftUp(int index);

    /**
     * Takes a node's index and returns the key of its parent node.
     *
     * @param index of a given node in the heap
     * @return node's parent's key
     */
    protected int parent(int index) {
        return this.heap.get((parentIndex(index)));
    }

    /**
     * Takes a node's index and returns its left child's key. If the node
     * doesn't have a child returns the node's own value
     *
     * @param index of a given node in the heap
     * @return Node's left child's key
     */
    protected int leftChild(int index) {

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
    protected int rightChild(int index) {

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
    protected void swap(int index_a, int index_b) {

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
    protected int parentIndex(int index) {

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
    protected int leftChildIndex(int index) {

        return 2 * index + 1;

    }

    /**
     * Calculates the right child of a node. Can make null references.Check the
     * index before using.
     *
     * @param index index of a given node
     * @return the node's right child
     */
    protected int rightChildIndex(int index) {

        return 2 * index + 2;
    }

}
