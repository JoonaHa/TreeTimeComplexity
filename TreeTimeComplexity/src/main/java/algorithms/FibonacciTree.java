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
public class FibonacciTree {

    private int key;
    private int order;
    private FibonacciTree parent;
    private FibonacciTree child;
    private FibonacciTree right;
    private FibonacciTree left;

    public FibonacciTree(int value) {
        this.key = value;
        this.order = 0;
    }

    public FibonacciTree link(FibonacciTree treeToBeLinked) {

        if (this.getOrder() != treeToBeLinked.getOrder()) {
            return null;
        }

        if (this.getKey() > treeToBeLinked.getKey()) {

            this.left.setRight(this.getRight());
            this.right.setLeft(this.getLeft());

            this.setParent(treeToBeLinked);
            this.setLeft(treeToBeLinked.getChild());
            this.setRight(treeToBeLinked.getChild().getLeft());
            treeToBeLinked.setChild(this);
            treeToBeLinked.increaseDegree();
            return treeToBeLinked;

        } else {

            //remove from linked list
            treeToBeLinked.left.setRight(treeToBeLinked.getRight());
            treeToBeLinked.right.setLeft(treeToBeLinked.getLeft());

            treeToBeLinked.setParent(this);
            treeToBeLinked.setLeft(this.getChild());
            treeToBeLinked.setRight(this.getChild().getLeft());
            this.setChild(treeToBeLinked);
            this.increaseDegree();

            return this;
        }

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

    public void increaseDegree() {
        this.order++;
    }

}
