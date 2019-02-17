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
public class MinFibonaciHeap {

    private FibonacciTree mini;
    private long size;

    public MinFibonaciHeap() {
        this.size = 0;
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

    }

    public int peek() {
        return mini.getKey();
    }

}
