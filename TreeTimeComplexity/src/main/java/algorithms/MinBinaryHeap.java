package algorithms;

import java.util.ArrayList;

/**
 *
 * @author JoonaHa
 */
public class MinBinaryHeap {

    private ArrayList<Integer> heap;

    public MinBinaryHeap() {
        this.heap = new ArrayList<>();
    }

    public int parent(int i) {
        return this.heap.get((i - 1) / 2);
    }

    public int leftChild(int i) {
        return this.heap.get((2 * i + 1));
    }

    public int rightChild(int i) {
        return this.heap.get((2 * i + 2));
    }

    public int right(int i) {
        return this.heap.get(i * 2 + 1);

    }

    public int left(int i) {
        return this.heap.get(i * 2 + 2);

    }

    public void insert(int insert) {

        this.heap.add(insert);
        int last = this.heap.size() - 1;
        int index = last;

        if (last != 0) {
            int parent = this.parent(index);

            while (index >= 0 && parent > insert) {

                swap(parentIndex(index), index);

                index = parentIndex(index);
                parent = this.parent(index);

            }
        }

    }

    public void delete(int index) {

        if (index == 0) {
            pop();

        } else {

            int value = Integer.MIN_VALUE;
            this.heap.set(index, value);

            int parent = this.parent(index);

            while (index >= 0 && parent > Integer.MIN_VALUE) {

                swap(parentIndex(index), index);

                index = parentIndex(index);
                parent = this.parent(index);

            }

        }

    }

    public int pop() {

        int rootValue = this.heap.get(0);

        int last = this.heap.size() - 1;

        if (last != 0) {

            swap(0, last);

            this.heap.remove(last);

            heapify(0);

        }

        return rootValue;

    }

    public int peek() {
        return this.heap.get(0);
    }

    private void swap(int index_a, int index_b) {

        int temporary = this.heap.get(index_a);

        this.heap.set(index_a, this.heap.get(index_b));

        this.heap.set(index_b, temporary);

    }

    private int parentIndex(int index) {

        if (index == 0) {
            return 0;
        }

        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {

        return 2 * index + 1;

    }

    private int rightChildIndex(int index) {

        return 2 * index + 2;

    }

    private void heapify(int index) {

        int last = this.heap.size() - 1;
        int value = this.heap.get(index);

        while (leftChildIndex(index) <= last) {

            
            int left = leftChild(index);
            int right = rightChild(index);

            if (left < value &&  left < right) {
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

}
