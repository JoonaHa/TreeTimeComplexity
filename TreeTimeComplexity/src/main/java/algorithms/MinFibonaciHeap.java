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

/**
 *
 * @author JoonaHa
 */
public class MinFibonaciHeap extends MinHeaps {

    private FibonacciTree mini;
    private int size;

    public MinFibonaciHeap() {
        this.size = 0;
    }

    public MinFibonaciHeap(GenericArrayList<Integer> values) {
        this.size = 0;

        for (int i = 0; i < values.size(); i++) {
            add(values.get(i));

        }
    }

    @Override
    public void add(int value) {

        FibonacciTree newTree = new FibonacciTree(value);

        if (mini != null) {
            newTree.setRight(mini);
            newTree.setLeft(mini.getLeft());
            mini.setLeft(newTree);

            newTree.getLeft().setRight(newTree);

            if (value < mini.getKey()) {
                mini = newTree;
            }

        } else {

            mini = newTree;

        }

        this.size++;

    }

    @Override
    public int peek() {
        return mini.getKey();
    }

    @Override
    public int pop() {
        FibonacciTree smallest = mini;

        if (smallest.getChild() != null) {

            smallest.getChild().setParent(null);

            for (FibonacciTree node = smallest.getChild().getRight();
                    node != smallest.getChild(); node = node.getRight()) {
                node.setParent(null);
            }

            //raise child nodes to root list
            FibonacciTree minLeft = mini.getLeft();
            FibonacciTree smallChildLeft = smallest.getChild().getLeft();
            mini.setLeft(smallChildLeft);

            smallChildLeft.setRight(mini);
            smallest.getChild().setLeft(minLeft);
            minLeft.setRight(smallest.getChild());

        }

        //remove mini from root list
        smallest.getLeft().setRight(smallest.getRight());
        smallest.getRight().setLeft(smallest.getLeft());

        if (smallest == smallest.getRight()) {
            mini = null;

        } else {
            mini = smallest.getRight();

            consolidate();
        }

        size--;

        return smallest.getKey();

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {

    }

    @Override
    public int decreaseKey(int value) {
        FibonacciTree node = findNode(value);

//        if (node == null) {
//            throw new NoSuchFieldError("No shuch value");
//        }

        node.decreaseKey();
        FibonacciTree parent = node.getParent();

        siftUp(node, parent);
        return value;
    }

    @Override
    public int delete(int value) {
        FibonacciTree node = findNode(value);

        node.setKey(Integer.MIN_VALUE);

        siftUp(node, node.getParent());

        pop();

        return value;

    }

    public FibonacciTree findNode(int value) {
        FibonacciTree start = mini;
        FibonacciTree iterate = start.getRight();

        if (mini.getKey() == value) {
            return mini;
        }
        while (iterate != start) {

            if (iterate.getKey() == value) {
                return iterate;
            }

            iterate = iterate.getRight();
        }

        return null;
    }

    private void consolidate() {

        FibonacciTree start = mini;
        FibonacciTree iterate = mini;

        //log2(max_size) the most root notes that can possibly be.
        FibonacciTree[] nodes = new FibonacciTree[45];

        do {

            FibonacciTree x = iterate;

            FibonacciTree nextIterate = iterate.getRight();

            int degree = x.getOrder();

            //if two trees with same degree link them.
            while (nodes[degree] != null) {

                FibonacciTree temp = nodes[degree];

                if (x.getKey() > temp.getKey()) {
                    FibonacciTree t = temp;
                    temp = x;
                    x = t;
                }

                if (temp == start) {
                    start = start.getRight();
                }

                if (temp == nextIterate) {
                    nextIterate = nextIterate.getRight();
                }

                nodes[degree] = null;
                degree++;

            }

            nodes[degree] = x;

            iterate = nextIterate;
        } while (iterate != start);

        mini = start;

        for (FibonacciTree n : nodes) {
            if (n != null) {
            }
            if (n != null && n.getKey() < mini.getKey()) {
                mini = n;

            }
        }

    }

    private void siftUp(FibonacciTree current, FibonacciTree parent) {
        if (parent != null && current.getKey() < parent.getKey()) {
            parent.cut(current, mini);
            parent.cascadingCut(mini);
        }

        if (current.getKey() < mini.getKey()) {
            mini = current;
        }

    }

}
