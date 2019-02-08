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
import java.util.Iterator;
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

        //To DO createHeap();
    }

    public void union(MinBinomialHeap otherHeap) {
        for (BinomialTree tree : otherHeap.getTrees()) {
            this.size += (int) Math.pow(tree.getOrder(), 2);

            if (this.roots.get(tree.getOrder()) != null) {
                this.roots.add(tree.getOrder(), tree);

            } else {
                this.roots.add(tree);
            }
        }
        int index = 0;
        BinomialTree prevX = null;
        BinomialTree X = this.roots.get(index);
        BinomialTree nextX = this.roots.get(index + 1);

        while (nextX != null) {
            if (X.getOrder() != nextX.getOrder()
                    || this.roots.get(index + 2) != null
                    && this.roots.get(index + 2).getOrder() == X.getOrder()) {
                prevX = X;
                X = nextX;
            } else {
                X.link(nextX);
                this.roots.remove(index + 1);

            }

        }
    }

    public LinkedList<BinomialTree> getTrees() {
        return this.roots;
    }

}
