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

    protected final ArrayList<Integer> heap;

    /**
     *
     * @param list to store the heap. Only accepts integers
     */
    public Heaps(ArrayList<Integer> list) {
        this.heap = list;
    }

    public int peek() {
        return this.heap.get(0);
    }

    /**
     * Abstract method to insert nodes. Implementation depends on the heap type.
     *
     * @param insert Value to insert to the heap.
     */
    public abstract void insert(int insert);

    /**
     * Abstract method to delete a value from a given index. Implementation
     * depends on the heap type.
     *
     * @param index Index to delete a value from
     */
    public abstract void delete(int index);
    /**
     * Abstract method that returns and delets the root node.
     * @return root node's key value
     */
    public abstract int pop();

    /**Sifts a node down to it's right level to keep the heap property
     *
     * @param index of a node that needs to be shifted
     */
    protected abstract void siftDown(int index);

    /**
     * Sifts a node up to it's right level to keep the heap property
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
