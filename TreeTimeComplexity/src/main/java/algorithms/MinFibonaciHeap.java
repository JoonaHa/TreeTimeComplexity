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

import utils.GenericArrayList;

/**
 * FibonacciHeap implementation based on
 * https://github.com/nlfiedler/graphmaker/blob/master/core/src/com/bluemarsh/graphmaker/core/util/FibonacciHeap.java
 *
 * @author JoonaHa
 */
public class MinFibonaciHeap implements MinHeaps {

    private FibonacciTree mini;
    private int size;

    /**
     * Create empty fibonacci heap
     */
    public MinFibonaciHeap() {
        this.size = 0;
    }

    /**
     * Create heap based on input data
     *
     * @param list data that will be made in to heap.
     */
    public MinFibonaciHeap(GenericArrayList<Integer> values) {
        this.size = 0;

        for (int i = 0; i < values.size(); i++) {
            add(values.get(i));

        }
    }

    /**
     * Add given value to fibonacci heap
     *
     * @param value
     */
    @Override
    public void add(int value) {

        FibonacciTree newTree = new FibonacciTree(value);

        //add new value to left of minimium value add linked other nodes to left of new node
        if (mini != null) {
            newTree.setRight(mini);
            newTree.setLeft(mini.getLeft());
            mini.setLeft(newTree);

            newTree.getLeft().setRight(newTree);

            if (value < mini.getKey()) {
                mini = newTree;
            }
            // if heap is empty update mini to new value

        } else {

            mini = newTree;

        }

        this.size++;

    }

    /**
     *
     * @return the smallest key of the heap
     */
    @Override
    public int peek() {
        return mini.getKey();
    }

    /**
     * Remove the heaps minimium value Also "optimises" the fiboncacci tree
     *
     * @return minimium value
     */
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

            //Optimses the fibonacci tree 
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
        this.mini = null;
        this.size = 0;

    }

    /**
     * Decrease the node with given value by one
     *
     * @param value to decrease
     * @return oldvalue
     */
    @Override
    public int decreaseKey(int value) {

        //Get node decrease it and siftup to maintain heap property
        FibonacciTree node = findNode(value);

        if (node == null) {
            throw new NoSuchFieldError("No shuch value");
        }

        node.decreaseKey();
        siftUp(node, node.getParent());
        return value;
    }

    /**
     * Delete node wih given value
     *
     * @param value Value to delete from heap.
     * @return old value
     */
    @Override
    public int delete(int value) {

        //set nodes key to MIN_VALUE sift it up to mini and pop it. 
        FibonacciTree node = findNode(value);

        node.setKey(Integer.MIN_VALUE);

        siftUp(node, node.getParent());

        pop();

        return value;

    }

    /**
     * Find value form heap O(n)
     *
     * @param value
     * @return
     */
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

    /**
     * Links Fibonacci trees of same order O(log N)
     */
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

    /**
     * Siftup from current node by cutting it or it's parent and adding them to
     * the root nodes.
     *
     * @param current Node to siftup from
     * @param parent Current nodes parent
     */
    private void siftUp(FibonacciTree current, FibonacciTree parent) {
        if (parent != null && current.getKey() < parent.getKey()) {
            parent.cut(current, mini);
            parent.cascadingCut(mini);
        }

        if (current.getKey() < mini.getKey()) {
            mini = current;
        }

    }

    /**
     * FiboncacciTree to used as a node for the heap
     */
    private static class FibonacciTree {

        private int key;
        private int order;
        private FibonacciTree parent;
        private FibonacciTree child;
        private FibonacciTree right;
        private FibonacciTree left;
        private boolean marked;

        /**
         * Create Tree with on starting value
         *
         * @param value
         */
        public FibonacciTree(int value) {
            this.key = value;
            this.order = 0;
            this.right = this;
            this.left = this;
        }

        /**
         * Link this node with a given child node
         *
         * @param child Node to link to this
         * @return Parent node
         */
        public FibonacciTree link(FibonacciTree child) {

            if (this.getOrder() != child.getOrder()) {
                return null;
            }

            //remove child from linked list
            child.getLeft().setRight(child.getRight());
            child.getRight().setLeft(child.getLeft());

            child.setParent(this);

            if (this.getChild() != null) {

                //update child sibling  with this childs
                child.setLeft(this.getChild());
                child.setRight(this.getChild().getRight());
                this.getChild().setRight(child);
                child.getRight().setLeft(child);

            } else {
                this.setChild(child);
                child.setRight(child);
                child.setLeft(child);
            }

            this.increaseDegree();
            marked = false;
            return this;

        }

        /**
         * Recursively cut marked nodes
         *
         * @param mini Fibonacci Trees minimiun value
         */
        public void cascadingCut(FibonacciTree mini) {
            FibonacciTree parent = this.parent;

            if (parent != null) {

                // if node is a child of another node recursively cut 
                if (this.isMarked()) {

                    parent.cut(this, mini);
                    parent.cascadingCut(mini);

                } else {
                    this.marked = true;
                }

            }
        }

        /**
         * Cut given node from linked list
         *
         * @param node
         * @param mini
         */
        private void cut(FibonacciTree node, FibonacciTree mini) {
            node.getLeft().setRight(node.getRight());
            node.getRight().setLeft(node.getLeft());
            this.decreaseDegree();

            if (this.order == 0) {
                this.child = null;
            } else if (this.child == node) {
                this.child = node.getRight();
            }

            node.setRight(mini);
            node.setLeft(mini.getLeft());
            mini.setLeft(node);
            node.getLeft().setRight(node);

            node.setParent(null);

            node.marked = false;
        }

        public FibonacciTree getChild() {
            return child;
        }

        public void setChild(FibonacciTree child) {
            this.child = child;
        }

        public void setRight(FibonacciTree sibling) {
            this.right = sibling;
        }

        public FibonacciTree getRight() {
            return right;
        }

        public void setLeft(FibonacciTree leftSibling) {
            this.left = leftSibling;
        }

        public FibonacciTree getLeft() {
            return left;
        }

        public FibonacciTree getParent() {
            return parent;
        }

        public int getKey() {
            return key;
        }

        public void setParent(FibonacciTree parent) {
            this.parent = parent;
        }

        public void decreaseKey() {
            this.key--;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getOrder() {
            return order;
        }

        public boolean isMarked() {
            return marked;
        }

        public void increaseDegree() {
            this.order++;
        }

        public void decreaseDegree() {
            this.order++;
        }
    }

}
