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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoonaHa
 */
public class MinBinaryHeapTest {

    private int[] items;
    private MinBinaryHeap heap;

    @Before
    public void setUp() {
        items = new int[]{3, 2, 3, 4, 5, 9, 7, 8};
        heap = new MinBinaryHeap();

        for (int i = 0; i < items.length; i++) {
            heap.insert(items[i]);

        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void MinHeapKeepsItOrderAfterInsert() {
        heap.insert(2);
        heap.insert(7);
        heap.insert(9);
        heap.insert(14);
        heap.insert(53);
        heap.insert(1);
        heap.insert(0);
        int compare = heap.peek();

        assertEquals(0, compare);
    }

    @Test
    public void MinHeapKeepsItOrderAfterPop() {

        testItemsToHeap();
        
        heap.insert(1);
        heap.insert(0);

        int compare1 = heap.pop();
        int compare2 = heap.peek();

        assertEquals(0, compare1);
        assertEquals(1, compare2);
    }

    private void testItemsToHeap() {

        for (int i = 0; i < items.length; i++) {
            heap.insert(items[i]);

        }
    }

}
