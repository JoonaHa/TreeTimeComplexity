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

            if (newTree.getLeft() != null) {
                newTree.getLeft().setRight(newTree);
            }

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
        int smallest = mini.getKey();

        FibonacciTree child = mini.getChild();

        if (child != null) {

            mini.getChild().setParent(null);

            //raise child nodes to root list
            FibonacciTree minLeft = mini.getLeft();
            mini.setLeft(child.getLeft());

            if (child.getLeft() != null) {
                child.getLeft().setRight(mini);
                child.setLeft(minLeft);
                minLeft.setRight(mini.getChild());
            }

            //remove mini from root list
            if (mini.getLeft() != null) {
                mini.getLeft().setRight(mini.getRight());
            }

            if (mini.getRight() != null) {
                mini.getRight().setLeft(mini.getLeft());
            }

        }

        consolidate();

        return smallest;

    }

    public long getSize() {
        return size;
    }

    private void consolidate() {

        FibonacciTree child = mini.getChild();
        FibonacciTree start = child;

        FibonacciTree[] nodes = new FibonacciTree[(int) (Math.log(size) / Math.log(2))];

        while (start != null) {

            FibonacciTree x = start.getRight();

            start.setParent(null);

            if (nodes[start.getOrder()] != null) {

                FibonacciTree temp = nodes[start.getOrder()];

                nodes[start.getOrder()] = temp.link(start);
            } else {
                nodes[start.getOrder()] = start;
            }

            start = x;
        }

        start = child;

        while (start != null) {

            FibonacciTree x = start.getLeft();

            start.setParent(null);

            if (nodes[start.getOrder()] != null) {

                FibonacciTree temp = nodes[start.getOrder()];

                nodes[start.getOrder()] = temp.link(start);
            } else {
                nodes[start.getOrder()] = start;
            }

            //check for left also
            start = x;
        }

        for (FibonacciTree n : nodes) {
            if (n != null && n.getKey() < child.getKey()) {
                mini = n;

            }
        }
    }

}
