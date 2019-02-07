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
public class BinomialTree {
    
       private class BinomialTreeNode {
        private int key;
        private int degree=0;
        private BinomialTreeNode parent;
        private BinomialTreeNode sibling;
        private BinomialTreeNode child;

        public BinomialTreeNode(int value) {
            this.key = value;

        }

        public BinomialTreeNode getChild() {
            return child;
        }

        public BinomialTreeNode getParent() {
            return parent;
        }

        public BinomialTreeNode getSibling() {
            return sibling;
        }

        public int getKey() {
            return key;
        }

        public void setChild(BinomialTreeNode child) {
            this.child = child;
        }

        public void setParent(BinomialTreeNode parent) {
            this.parent = parent;
        }

        public void setSibling(BinomialTreeNode sibling) {
            this.sibling = sibling;
        }

        public void setKey(int key) {
            this.key = key;
        }
        
        
        
        
        
        
        
    
    
}
    
}
