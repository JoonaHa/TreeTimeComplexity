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
public class MinBinomialHeap extends MinHeaps {

    private GenericArrayList<BinomialTreeDemo> roots;
    private int size;

    public MinBinomialHeap() {
        this.roots = new GenericArrayList<>();
        this.size = 0;
    }

    public MinBinomialHeap(GenericArrayList<Integer> list) {
        this.roots = new GenericArrayList<>();
        this.size = 0;

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));

        }

    }

    @Override
    public void add(int value) {
        MinBinomialHeap insert = new MinBinomialHeap();
        insert.insertNewNode(new BinomialTreeDemo(value));
        this.union(insert);
        this.size++;
    }

    @Override
    public int peek() {

        return roots.get(findMinIndex()).getKey();
    }

    @Override
    public int pop() {
        int minIndex = findMinIndex();
        BinomialTreeDemo min = roots.get(minIndex);
        roots.remove(minIndex);

        if (min.hasChild()) {

            BinomialTreeDemo child = min.getChild();

            MinBinomialHeap heap = new MinBinomialHeap();

            if (child.hasSibling()) {

                BinomialTreeDemo sibling = child.getSibling();
                BinomialTreeDemo old;
                child.setSibling(null);

                while (sibling != null) {
                    old = sibling;

                    sibling = sibling.getSibling();
                    old.setSibling(null);
                    heap.insertNewNode(old);

                }
            }

            heap.insertNewNode(child);
            this.union(heap);
        }

        this.size--;
        return min.getKey();

    }

    public GenericArrayList<BinomialTreeDemo> getTrees() {
        return this.roots;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int decreaseKey(int value) {

        BinomialTreeDemo decreaseThis = findNode(value);

        int oldaValue = decreaseThis.getKey();
        decreaseThis.decreaseKey();

        siftUp(decreaseThis);

        return oldaValue;
    }

    @Override
    public int delete(int value) {

        BinomialTreeDemo oldNode = findNode(value);

        int oldValue = oldNode.getKey();

        oldNode.setKey(Integer.MIN_VALUE);

        siftUp(oldNode);

        pop();

        return oldValue;
    }

    @Override
    public void clear() {
        this.roots = new GenericArrayList<>();
        this.size = 0;

    }

    private void union(MinBinomialHeap otherHeap) {
        GenericArrayList<BinomialTreeDemo> others = otherHeap.getTrees();

        for (int i = 0; i < others.size(); i++) {
            this.roots.add(others.get(i));

        }

        BinomialTreeDemo[] temp = new BinomialTreeDemo[roots.size()];

        for (int i = 0; i < roots.size(); i++) {
            temp[i] = this.roots.get(i);

        }

        sortbyOrder(temp);
        this.roots = new GenericArrayList<>();

        for (int i = 0; i < temp.length; i++) {
            this.roots.add(temp[i]);
        }

        int index = 0;
        int nextIndex = 1;

        while (nextIndex < roots.size()) {
            BinomialTreeDemo x = this.roots.get(index);
            BinomialTreeDemo nextX = this.roots.get(nextIndex);

            if (x.getOrder() != nextX.getOrder()) {
                index++;
                nextIndex++;

            } else if (nextIndex + 1 < roots.size()
                    && this.roots.get(nextIndex + 1).getOrder() == x.getOrder()) {

                index++;
                nextIndex++;
            } else {
                this.roots.set(index, x.link(nextX));
                this.roots.remove(nextIndex);
            }

        }
    }

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

    public BinomialTreeDemo findNode(int value) {

        //Check roots first
        for (int i = 0; i < roots.size(); i++) {

            if (roots.get(i).getKey() == value) {
                return roots.get(i);
            }
        }

        //recurse if needed
        BinomialTreeDemo ret = null;
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

    private BinomialTreeDemo recurseTree(int value, BinomialTreeDemo tree) {

        if (tree == null) {
            return null;
        }

        if (tree.getKey() == value) {
            return tree;
        }

        BinomialTreeDemo child = recurseTree(value, tree.getChild());
        if (child != null) {
            return child;
        }

        return recurseTree(value, tree.getSibling());
    }

    private void insertNewNode(BinomialTreeDemo newNode) {
        this.roots.add(newNode);
    }

    private void siftUp(BinomialTreeDemo node) {

        int value = node.getKey();
        BinomialTreeDemo parent = node.getParent();

        //swap node with its parent till its parent is smaller 
        //or the node is root
        while (parent != null && parent.getKey() > value) {

            swap(parent, node);

            node = parent;
            parent = parent.getParent();

        }

    }

    private void swap(BinomialTreeDemo a, BinomialTreeDemo b) {

        int temporary = a.getKey();

        a.setKey(b.getKey());
        b.setKey(temporary);

    }

    private void sortbyOrder(BinomialTreeDemo[] trees) {

        int max = trees[0].getOrder();
        for (int i = 1; i < trees.length; i++) {
            if (trees[i].getOrder() > max) {
                max = trees[i].getOrder();
            }
        }

        int[] histogram = new int[max + 1];

        for (int i = 0; i < trees.length; i++) {
            histogram[trees[i].getOrder()]++;
        }

        for (int i = 0; i <= max - 1; i++) {
            histogram[i] += histogram[i + 1];
        }

        BinomialTreeDemo[] newArray = new BinomialTreeDemo[trees.length];

        for (int i = trees.length - 1; i >= 0; i--) {
            newArray[histogram[trees[i].getOrder()] - 1] = trees[i];
            --histogram[trees[i].getOrder()];
        }

        System.arraycopy(trees, 0, newArray, 0, trees.length);
    }

    public static class BinomialTreeDemo {

        private int key;
        private int order;
        private BinomialTreeDemo parent;
        private BinomialTreeDemo child;
        private BinomialTreeDemo sibling;

        public BinomialTreeDemo(int value) {
            this.key = value;
            this.order = 0;
        }

        public BinomialTreeDemo link(BinomialTreeDemo treeToBeLinked) {

            if (this.getOrder() != treeToBeLinked.getOrder()) {
                return null;
            }

            if (this.getKey() > treeToBeLinked.getKey()) {
                this.setParent(treeToBeLinked);
                this.setSibling(treeToBeLinked.getChild());
                treeToBeLinked.setChild(this);
                treeToBeLinked.increaseDegree();
                return treeToBeLinked;
            } else {
                treeToBeLinked.setParent(this);
                treeToBeLinked.setSibling(this.getChild());
                this.setChild(treeToBeLinked);
                this.increaseDegree();

                return this;
            }

        }

        public BinomialTreeDemo getChild() {
            return child;
        }

        public void setChild(BinomialTreeDemo child) {
            this.child = child;
        }

        public void setSibling(BinomialTreeDemo sibling) {
            this.sibling = sibling;
        }

        public BinomialTreeDemo getSibling() {
            return sibling;
        }

        public BinomialTreeDemo getParent() {
            return parent;
        }

        public int getKey() {
            return key;
        }

        public void setParent(BinomialTreeDemo parent) {
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
