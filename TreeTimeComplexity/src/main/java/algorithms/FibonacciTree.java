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
    private boolean marked;

    public FibonacciTree(int value) {
        this.key = value;
        this.order = 0;
        this.right = this;
        this.left = this;
    }

    public FibonacciTree link(FibonacciTree child) {

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

    public void cascadingCut(FibonacciTree mini) {
        FibonacciTree parent = this.parent;

        if (parent != null) {

            if (this.isMarked()) {
                
                parent.cut(this, mini);
                parent.cascadingCut(mini);
                
            } else {
                this.marked = true;
            }

        }
    }

    public void cut(FibonacciTree start, FibonacciTree mini) {
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
