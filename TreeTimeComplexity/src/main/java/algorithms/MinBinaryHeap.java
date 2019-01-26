package algorithms;

import java.util.ArrayList;

/**
 *
 * @author JoonaHa
 */
public class MinBinaryHeap extends Heaps {

    public MinBinaryHeap(ArrayList<Integer> list) {
        super(list);
    }

    @Override
    public void insert(int insert) {

        this.heap.add(insert);
        int last = this.heap.size() - 1;
        int index = last;

        if (last != 0) {

            siftUp(index);
        }

    }

    @Override
    public void delete(int index) {

        if (index == 0) {
            pop();

        } else {

            int value = Integer.MIN_VALUE;
            this.heap.set(index, value);

            siftUp(index);

            pop();

        }

    }

    @Override
    public int pop() {

        int rootValue = this.heap.get(0);

        int last = this.heap.size() - 1;

        if (last != 0) {

            super.swap(0, last);

            this.heap.remove(last);

            siftDown(0);

        }

        return rootValue;

    }

    @Override
    protected void siftDown(int index) {

        int last = this.heap.size() - 1;
        int value = this.heap.get(index);

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

    @Override
    protected void siftUp(int index) {

        int value = this.heap.get(index);
        int parent = this.parent(index);

        while (index >= 0 && parent > value) {

            swap(parentIndex(index), index);

            index = parentIndex(index);
            parent = this.parent(index);

        }
    }

}
