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
 * Interface for the basic operations of a heap. Creates a dynamic heap.
 *
 *
 * @author JoonaHa
 */
public interface MinHeaps {

    public abstract int peek();

    /**
     * Abstract method to add nodes. Implementation depends on the heap type.
     *
     * @param insert Value to add to the heap.
     */
    public void add(int insert);

    /**
     * Abstract method to delete a value from a given index. Implementation
     * depends on the heap type.
     *
     * @return removed node's value
     * @param value value to delete a value from
     */
    public int delete(int value);

    /**
     * Abstract method that returns and delets the root node.
     *
     * @return root node's key value
     */
    public int pop();

    /**
     * Decrease key by 1 form a given index. Return the old value of the key
     *
     * @param value where which value will be incremented
     * @return value of the old key
     */
    public int decreaseKey(int value);

    public void clear();

    public int getSize();

}
