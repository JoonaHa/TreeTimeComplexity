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
 *
 * @author JoonaHa
 */
public class MinFibonaciHeap extends MinHeaps {

    private FibonacciTreeDemo mini;
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

        FibonacciTreeDemo newTree = new FibonacciTreeDemo(value);

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
        FibonacciTreeDemo smallest = mini;

        if (smallest.getChild() != null) {

            smallest.getChild().setParent(null);

            for (FibonacciTreeDemo node = smallest.getChild().getRight();
                    node != smallest.getChild(); node = node.getRight()) {
                node.setParent(null);
            }

            //raise child nodes to root list
            FibonacciTreeDemo minLeft = mini.getLeft();
            FibonacciTreeDemo smallChildLeft = smallest.getChild().getLeft();
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
        this.mini = null;
        this.size = 0;

    }

    @Override
    public int decreaseKey(int value) {
        FibonacciTreeDemo node = findNode(value);

        if (node == null) {
            throw new NoSuchFieldError("No shuch value");
        }

        node.decreaseKey();
        siftUp(node, node.getParent());
        return value;
    }

    @Override
    public int delete(int value) {
        FibonacciTreeDemo node = findNode(value);

        node.setKey(Integer.MIN_VALUE);

        siftUp(node, node.getParent());

        pop();

        return value;

    }

    public FibonacciTreeDemo findNode(int value) {
        FibonacciTreeDemo start = mini;
        FibonacciTreeDemo iterate = start.getRight();

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

        FibonacciTreeDemo start = mini;
        FibonacciTreeDemo iterate = mini;

        //log2(max_size) the most root notes that can possibly be.
        FibonacciTreeDemo[] nodes = new FibonacciTreeDemo[45];

        do {

            FibonacciTreeDemo x = iterate;

            FibonacciTreeDemo nextIterate = iterate.getRight();

            int degree = x.getOrder();

            //if two trees with same degree link them.
            while (nodes[degree] != null) {

                FibonacciTreeDemo temp = nodes[degree];

                if (x.getKey() > temp.getKey()) {
                    FibonacciTreeDemo t = temp;
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

        for (FibonacciTreeDemo n : nodes) {
            if (n != null) {
            }
            if (n != null && n.getKey() < mini.getKey()) {
                mini = n;

            }
        }

    }

    private void siftUp(FibonacciTreeDemo current, FibonacciTreeDemo parent) {
        if (parent != null && current.getKey() < parent.getKey()) {
            parent.cut(current, mini);
            parent.cascadingCut(mini);
        }

        if (current.getKey() < mini.getKey()) {
            mini = current;
        }

    }

    private static class FibonacciTreeDemo {

        private int key;
        private int order;
        private FibonacciTreeDemo parent;
        private FibonacciTreeDemo child;
        private FibonacciTreeDemo right;
        private FibonacciTreeDemo left;
        private boolean marked;

        public FibonacciTreeDemo(int value) {
            this.key = value;
            this.order = 0;
            this.right = this;
            this.left = this;
        }

        public FibonacciTreeDemo link(FibonacciTreeDemo child) {

            if (this.getOrder() != child.getOrder()) {
                return null;
            }

            //remove child from linked list
            child.getLeft().setRight(child.getRight());
            child.getRight().setLeft(child.getLeft());

            child.setParent(this);

            if (this.getChild() != null) {

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

        public void cascadingCut(FibonacciTreeDemo mini) {
            FibonacciTreeDemo parent = this.parent;

            if (parent != null) {

                if (this.isMarked()) {

                    parent.cut(this, mini);
                    parent.cascadingCut(mini);

                } else {
                    this.marked = true;
                }

            }
        }

        public void cut(FibonacciTreeDemo start, FibonacciTreeDemo mini) {
            start.getLeft().setRight(start.getRight());
            start.getRight().setLeft(start.getLeft());
            this.decreaseDegree();

            if (this.order == 0) {
                this.child = null;
            } else if (this.child == start) {
                this.child = start.getRight();
            }

            start.setRight(mini);
            start.setLeft(mini.getLeft());
            mini.setLeft(start);
            start.getLeft().setRight(start);

            start.setParent(null);

            start.marked = false;
        }

        public FibonacciTreeDemo getChild() {
            return child;
        }

        public void setChild(FibonacciTreeDemo child) {
            this.child = child;
        }

        public void setRight(FibonacciTreeDemo sibling) {
            this.right = sibling;
        }

        public FibonacciTreeDemo getRight() {
            return right;
        }

        public void setLeft(FibonacciTreeDemo leftSibling) {
            this.left = leftSibling;
        }

        public FibonacciTreeDemo getLeft() {
            return left;
        }

        public FibonacciTreeDemo getParent() {
            return parent;
        }

        public int getKey() {
            return key;
        }

        public void setParent(FibonacciTreeDemo parent) {
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
