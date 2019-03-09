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
 * Minimium Binomial implementation
 *
 * @author JoonaHa
 */
public class MinBinomialHeap implements MinHeaps {

    private GenericArrayList<BinomialTree> roots;
    private int size;

    /**
     * Create empty heap
     */
    public MinBinomialHeap() {
        this.roots = new GenericArrayList<>();
        this.size = 0;
    }

    /**
     * Create heap based on input data
     *
     * @param list data that will be made in to heap.
     */
    public MinBinomialHeap(GenericArrayList<Integer> list) {
        this.roots = new GenericArrayList<>();
        this.size = 0;

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));

        }

    }

    /**
     * Add integer value
     *
     * @param value value to add to binomial heap
     */
    @Override
    public void add(int value) {
        //Make new heap and merge it. Increase size
        MinBinomialHeap insert = new MinBinomialHeap();
        insert.insertNewNode(new BinomialTree(value));
        this.union(insert);
        this.size++;
    }

    /**
     * Returns the minimium value of the heap
     *
     * @return minmiun value of the heap
     */
    @Override
    public int peek() {

        return roots.get(findMinIndex()).getKey();
    }

    /**
     * Returns the minimium value of the heap and removes it
     *
     * @return minimiun value of the heap
     */
    @Override
    public int pop() {
        //find minimuin value from root list
        int minIndex = findMinIndex();
        BinomialTree min = roots.get(minIndex);
        roots.remove(minIndex);

        //Add childs to new  heap
        if (min.hasChild()) {

            BinomialTree child = min.getChild();

            MinBinomialHeap heap = new MinBinomialHeap();

            if (child.hasSibling()) {

                BinomialTree sibling = child.getSibling();
                BinomialTree old;
                child.setSibling(null);

                while (sibling != null) {
                    old = sibling;

                    sibling = sibling.getSibling();
                    old.setSibling(null);
                    heap.insertNewNode(old);

                }
            }

            heap.insertNewNode(child);

            //merge the new heap with childs
            this.union(heap);
        }

        this.size--;
        return min.getKey();

    }

    /**
     *
     * @return Return root list with it's Binomial trees
     */
    private GenericArrayList<BinomialTree> getTrees() {
        return this.roots;
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * Decreases given key with one
     *
     * @param value to search in a tree
     * @return old value
     */
    @Override
    public int decreaseKey(int value) {

        //Find node and and siftup to keep the heap property
        BinomialTree decreaseThis = findNode(value);

        int oldaValue = decreaseThis.getKey();
        decreaseThis.decreaseKey();

        siftUp(decreaseThis);

        return oldaValue;
    }

    /**
     * Delete node with given value
     *
     * @param value to be deleted
     * @return old value
     */
    @Override
    public int delete(int value) {

        //find node, replace it's value by MIN_VALUE
        //siftup and reomve it with pop
        BinomialTree oldNode = findNode(value);

        int oldValue = oldNode.getKey();

        oldNode.setKey(Integer.MIN_VALUE);

        siftUp(oldNode);

        pop();

        return oldValue;
    }

    /**
     * clear heap
     */
    @Override
    public void clear() {
        this.roots = new GenericArrayList<>();
        this.size = 0;

    }

    /**
     * Merge two heaps.
     *
     * @param otherHeap to merge with
     */
    private void union(MinBinomialHeap otherHeap) {
        GenericArrayList<BinomialTree> others = otherHeap.getTrees();

        // Add otherheaps root trees and to this heaps root.
        for (int i = 0; i < others.size(); i++) {
            this.roots.add(others.get(i));

        }

        //Sort roots by order and reset roots
        BinomialTree[] temp = new BinomialTree[roots.size()];

        for (int i = 0; i < roots.size(); i++) {
            temp[i] = this.roots.get(i);

        }
        sortbyOrder(temp);

        this.roots = new GenericArrayList<>();

        for (int i = 0; i < temp.length; i++) {
            this.roots.add(temp[i]);
        }

        //Link to binomial trees if they are the same order
        int index = 0;
        int nextIndex = 1;

        while (nextIndex < roots.size()) {
            BinomialTree x = this.roots.get(index);
            BinomialTree nextX = this.roots.get(nextIndex);

            if (x.getOrder() != nextX.getOrder()) {
                index++;
                nextIndex++;

                //Link if nextIndex index are the same order
                //if index and nextIndex +1 is same size move ahead.
            } else if (nextIndex + 1 < roots.size()
                    && this.roots.get(nextIndex + 1).getOrder() == x.getOrder()) {

                index++;
                nextIndex++;
            } else {
                //link trees and remove the child node form root list
                this.roots.set(index, x.link(nextX));
                this.roots.remove(nextIndex);
            }

        }
    }

    /**
     * Find min index from roots list. O(log n)
     *
     * @return
     */
    private int findMinIndex() {
        int min = roots.get(0).getKey();
        int index = 0;
        for (int i = 0; i < roots.size(); i++) {
            if (roots.get(i).getKey() < min) {
                min = roots.get(i).getKey();
                index = i;
            }

        }

        return index;
    }

    /**
     * Finde node with give value
     *
     * @param value
     * @return Node with given value
     */
    public BinomialTree findNode(int value) {

        //Check roots first
        for (int i = 0; i < roots.size(); i++) {

            if (roots.get(i).getKey() == value) {
                return roots.get(i);
            }
        }

        //recurse if needed
        BinomialTree ret = null;
        for (int i = 0; i < roots.size(); i++) {

            if (roots.get(i).getKey() < value) {

                ret = recurseTree(value, roots.get(i));
                if (ret != null) {
                    break;
                }

            }

        }

        return ret;
    }

    /**
     * Tail recursive function for recursing the BinomialTree
     *
     * @param value key to be saerched for
     * @param tree tree to recurse trough
     * @return node
     */
    private BinomialTree recurseTree(int value, BinomialTree tree) {

        if (tree == null) {
            return null;
        }

        if (tree.getKey() == value) {
            return tree;
        }

        BinomialTree child = recurseTree(value, tree.getChild());
        if (child != null) {
            return child;
        }

        return recurseTree(value, tree.getSibling());
    }

    /**
     * Add new node to root list
     *
     * @param newNode to be added
     */
    private void insertNewNode(BinomialTree newNode) {
        this.roots.add(newNode);
    }

    /**
     * Siftup to maintain the heap value
     *
     * @param node Node to siftup from
     */
    private void siftUp(BinomialTree node) {

        int value = node.getKey();
        BinomialTree parent = node.getParent();

        //swap node with its parent till its parent is smaller 
        //or the node is root
        while (parent != null && parent.getKey() > value) {

            swap(parent, node);

            node = parent;
            parent = parent.getParent();

        }

    }

    /**
     * Swap keys of two nodes
     *
     * @param a Node 1
     * @param b Node 2
     */
    private void swap(BinomialTree a, BinomialTree b) {

        int temporary = a.getKey();

        a.setKey(b.getKey());
        b.setKey(temporary);

    }

    /**
     * Countig sort by binomialheap order
     *
     * @param trees Array of trees to sort,
     */
    private void sortbyOrder(BinomialTree[] trees) {

        int max = trees[0].getOrder();
        for (int i = 1; i < trees.length; i++) {
            if (trees[i].getOrder() > max) {
                max = trees[i].getOrder();
            }
        }

        int[] counts = new int[max + 1];
        for (int i = 0; i < trees.length; i++) {
            counts[trees[i].getOrder()]++;
        }

        int total = 0;
        for (int i = 0; i <= max; i++) {
            int count = counts[i];
            counts[i] = total;
            total += count;
        }

        BinomialTree[] newArray = new BinomialTree[trees.length];
        for (int i = 0; i < trees.length; i++) {
            newArray[counts[trees[i].getOrder()]] = trees[i];
            counts[trees[i].getOrder()]++;
        }

        System.arraycopy(trees, 0, newArray, 0, trees.length);
    }

    /**
     * Binomial tree.
     */
    public static class BinomialTree {

        private int key;
        private int order;
        private BinomialTree parent;
        private BinomialTree child;
        private BinomialTree sibling;

        /**
         * Create Binomial tree with give starting value
         *
         * @param value
         */
        public BinomialTree(int value) {
            this.key = value;
            this.order = 0;
        }

        /**
         * Links too Binmialtreess Return the parent node after comparison
         *
         * @param treeToBeLinked
         * @return The parent node of the linked tree
         */
        public BinomialTree link(BinomialTree treeToBeLinked) {

            if (this.getOrder() != treeToBeLinked.getOrder()) {
                return null;
            }

            //Make treeToBeLinked parent
            if (this.getKey() > treeToBeLinked.getKey()) {
                this.setParent(treeToBeLinked);
                this.setSibling(treeToBeLinked.getChild());
                treeToBeLinked.setChild(this);
                treeToBeLinked.increaseDegree();
                return treeToBeLinked;
            } else {

                //Make this parent
                treeToBeLinked.setParent(this);
                treeToBeLinked.setSibling(this.getChild());
                this.setChild(treeToBeLinked);
                this.increaseDegree();

                return this;
            }

        }

        public BinomialTree getChild() {
            return child;
        }

        public void setChild(BinomialTree child) {
            this.child = child;
        }

        public void setSibling(BinomialTree sibling) {
            this.sibling = sibling;
        }

        public BinomialTree getSibling() {
            return sibling;
        }

        public BinomialTree getParent() {
            return parent;
        }

        public int getKey() {
            return key;
        }

        public void setParent(BinomialTree parent) {
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

        public Boolean hasSibling() {
            if (this.sibling == null) {
                return false;
            }

            return true;
        }

        public Boolean hasChild() {
            if (this.child == null) {
                return false;
            }

            return true;
        }

        public void increaseDegree() {
            this.order++;
        }

    }

}
