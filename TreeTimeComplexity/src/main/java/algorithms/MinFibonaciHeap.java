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
 * @author JoonaHa
 */
public class MinFibonaciHeap {

    private FibonacciTree mini;
    private long size;

    public MinFibonaciHeap() {
        this.size = 0;
    }

    public MinFibonaciHeap(ArrayList<Integer> value) {
        this.size = 0;

        for (Integer integer : value) {
            this.add(integer);
        }
    }

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

    public int peek() {
        return mini.getKey();
    }

    public int pop() {
        FibonacciTree smallest = mini;

        if (smallest.getChild() != null) {

            smallest.getChild().setParent(null);

            for (FibonacciTree node = smallest.getChild().getRight(); node != smallest.getChild(); node = node.getRight()) {
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

    public long getSize() {
        return size;
    }

    private void consolidate() {

        FibonacciTree start = mini;
        FibonacciTree iterate = mini;
        System.out.println(size);

        //log2(max_size) the most root notes that can possibly be.
        FibonacciTree[] nodes = new FibonacciTree[45];
        System.out.println(nodes.length);

        do {

            FibonacciTree x = iterate;

            FibonacciTree nextIterate = iterate.getRight();

            int degree = x.getOrder();

            //if two trees with same degree link them.
            while (nodes[degree] != null) {

                FibonacciTree temp = nodes[degree];
                nodes[degree] = temp.link(x);
                temp = nodes[degree].getChild();

                if (temp == start) {
                    start = start.getRight();
                }

                if (temp == nextIterate) {
                    nextIterate = nextIterate.getRight();
                }

                nodes[degree] = null;
                degree++;
                System.out.println(degree);

            }

            nodes[degree] = x;

            iterate = nextIterate;
        } while (iterate != start);

        mini = start;

        for (FibonacciTree n : nodes) {
            if (n != null && n.getKey() < mini.getKey()) {
                mini = n;

            }
        }
    }

}
