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
import java.util.LinkedList;

/**
 *
 * @author JoonaHa
 */
public class MinBinomialHeap {

    private LinkedList<BinomialTree> roots;
    private int size;

    public MinBinomialHeap() {
        this.roots = new LinkedList<>();
        this.size = 0;
    }

    public MinBinomialHeap(ArrayList<Integer> list) {
        this.roots = new LinkedList<>();
        this.size = 0;

        for (Integer integer : list) {
            this.add(integer.intValue());
        }

    }

    public void add(int value) {
        MinBinomialHeap insert = new MinBinomialHeap();
        insert.inserNewNode(new BinomialTree(value));
        this.union(insert);
        this.size++;
    }

    public int peek() {

        return roots.get(findMinIndex()).getKey();
    }

    public int pop() {
        int minIndex = findMinIndex();
        int min = roots.get(minIndex).getKey();

        if (roots.get(minIndex).hasChild()) {

            BinomialTree child = roots.get(minIndex).getChild();

            roots.remove(minIndex);

            MinBinomialHeap heap = new MinBinomialHeap();

            heap.inserNewNode(child);

            if (child.hasSibling()) {

                BinomialTree sibling = child.getSibling();

                while (sibling.hasSibling()) {
                    heap.inserNewNode(sibling);
                    BinomialTree old = sibling;

                    if (!sibling.hasSibling()) {
                        break;
                    }

                    sibling = sibling.getSibling();
                    old.setSibling(null);

                }
            }

            this.union(heap);
        }

        this.size--;
        return min;

    }

    public LinkedList<BinomialTree> getTrees() {
        return this.roots;
    }

    public int getSize() {
        return size;
    }

    public int decreaseKey(int index) {

        BinomialTree decreaseThis = findNodeFromIndex(index);

        int oldaValue = decreaseThis.getKey();
        decreaseThis.decreaseKey();

        siftUp(decreaseThis);

        return oldaValue;
    }

    public int delete(int index) {

        BinomialTree oldNode = findNodeFromIndex(index);

        int oldValue = oldNode.getKey();

        oldNode.setKey(Integer.MIN_VALUE);

        siftUp(oldNode);

        pop();

        return oldValue;
    }

    private void union(MinBinomialHeap otherHeap) {
        for (BinomialTree tree : otherHeap.getTrees()) {

            if (roots.size() < tree.getOrder()) {
                this.roots.add(tree.getOrder(), tree);

            } else {
                this.roots.add(tree);
            }
        }
        int index = 0;
        int nextIndex = 1;

        while (nextIndex < roots.size()) {
            BinomialTree x = this.roots.get(index);
            BinomialTree nextX = this.roots.get(nextIndex);

            if (x.getOrder() != nextX.getOrder()) {
                index++;

            } else if (nextIndex + 1 < roots.size()
                    && this.roots.get(nextIndex + 1).getOrder() == x.getOrder()) {

                index++;
            } else {
                this.roots.set(index, x.link(nextX));
                this.roots.remove(nextIndex);
            }

            nextIndex++;

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

    private BinomialTree findNodeFromIndex(int index) {

        if (index == 0) {
            return this.roots.get(0);
        }

        int whichroot = 0;
        int remainer = 0;
        while (whichroot < roots.size()) {
            remainer += (int) Math.pow(2, this.roots.get(whichroot).getOrder());
            if (index - remainer > 0) {
                whichroot++;
            } else {
                break;
            }
        }

        if (index - remainer == 0) {
            return roots.get(whichroot++);
        }
        System.out.println(index - remainer);
        System.out.println((int) Math.pow(2, this.roots.get(whichroot).getOrder()));
        int child = (int) (Math.pow(2, this.roots.get(whichroot).getOrder()) + (index - remainer)) - 1;
        System.out.println(child);
        return roots.get(whichroot); //.getChildren().get(child);

    }

    protected void inserNewNode(BinomialTree newNode) {
        this.roots.add(newNode);
    }

    protected void siftUp(BinomialTree node) {

        int value = node.getKey();
        BinomialTree parent = node.getParent();

        //swap node with its parent till its parent is smaller 
        //or the node is root
        while (parent != null && parent.getKey() > value) {

            swap(parent, node);

            value = parent.getKey();
            parent = parent.getParent();

        }

    }

    protected void swap(BinomialTree a, BinomialTree b) {

        BinomialTree temporary = a;

        a.setKey(b.getKey());
        b.setKey(temporary.getKey());

    }

}
