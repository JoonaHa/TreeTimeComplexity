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
import java.util.Collections;

/**
 *
 * @author JoonaHa
 */
public class MinBinomialHeap {

    private ArrayList<BinomialTree> roots;
    private int size;

    public MinBinomialHeap() {
        this.roots = new ArrayList<>();
        this.size = 0;
    }

    public MinBinomialHeap(ArrayList<Integer> list) {
        this.roots = new ArrayList<>();
        this.size = 0;

        list.forEach((integer) -> {
            this.add(integer);
        });

    }

    public void add(int value) {
        MinBinomialHeap insert = new MinBinomialHeap();
        insert.insertNewNode(new BinomialTree(value));
        this.union(insert);
        this.size++;
    }

    public int peek() {

        return roots.get(findMinIndex()).getKey();
    }

    public int pop() {
        int minIndex = findMinIndex();
        BinomialTree min = roots.get(minIndex);
        roots.remove(minIndex);

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
            this.union(heap);
        }

        this.size--;
        return min.getKey();

    }

    public ArrayList<BinomialTree> getTrees() {
        return this.roots;
    }

    public int getSize() {
        return size;
    }

    public int decreaseKey(int value) {

        BinomialTree decreaseThis = findNode(value);

        int oldaValue = decreaseThis.getKey();
        decreaseThis.decreaseKey();

        siftUp(decreaseThis);

        return oldaValue;
    }

    public int delete(int value) {

        BinomialTree oldNode = findNode(value);

        int oldValue = oldNode.getKey();

        oldNode.setKey(Integer.MIN_VALUE);

        siftUp(oldNode);

        pop();

        return oldValue;
    }

    private void union(MinBinomialHeap otherHeap) {
        for (BinomialTree tree : otherHeap.getTrees()) {

            roots.add(tree);
        }

        Collections.sort(roots, (a, b) -> a.getOrder() < b.getOrder() ? -1 : a.getOrder() == b.getOrder() ? 0 : 1);

        int index = 0;
        int nextIndex = 1;

        while (nextIndex < roots.size()) {
            BinomialTree x = this.roots.get(index);
            BinomialTree nextX = this.roots.get(nextIndex);

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

    private BinomialTree findNode(int value) {

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

    protected void insertNewNode(BinomialTree newNode) {
        this.roots.add(newNode);
    }

    protected void siftUp(BinomialTree node) {

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

    protected void swap(BinomialTree a, BinomialTree b) {

        int temporary = a.getKey();

        a.setKey(b.getKey());
        b.setKey(temporary);

    }

}
