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

/**
 *
 * @author JoonaHa
 */
public class BinomialTree {

    private int key;
    private int order = 0;
    private BinomialTree parent;
    private ArrayList<BinomialTree> childs;

    public BinomialTree(int value) {
        this.key = value;
        this.childs = new ArrayList<>();

    }

    public BinomialTree link(BinomialTree treeToBeLinked) {

        if (this.getOrder() != treeToBeLinked.getOrder()) {
            return null;
        }

        if (this.getKey() > treeToBeLinked.getKey()) {
            treeToBeLinked.addChild(this);
            treeToBeLinked.increaseDegree();
            return treeToBeLinked;
        } else {
            this.addChild(treeToBeLinked);
            this.increaseDegree();
            return this;
        }

    }

    public ArrayList<BinomialTree> getChildren() {
        return childs;
    }

    public void addChild(BinomialTree child) {
        this.childs.add(child);
        child.setParent(this);
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
    
    public void increaseDegree() {
        this.order++;
    }

}
