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
import java.util.PriorityQueue;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JoonaHa
 */
public class MinFibonaciHeapTest {

    public MinFibonaciHeapTest() {
    }

    public void MinHeapCreateHeapFromExisitingList() {
        ArrayList<Integer> data = createTestData(1000);

        PriorityQueue<Integer> compHeap = new PriorityQueue<>(data);

        MinFibonaciHeap testHeap = new MinFibonaciHeap(data);

        for (int i = 0; i < data.size(); i++) {

            assertEquals((int) compHeap.poll(), testHeap.pop());

        }

    }

    @Test
    public void MinHeapKeepsItOrderAfterInsert() {

        PriorityQueue<Integer> compHeap = new PriorityQueue<>(createTestData(1000));

        MinFibonaciHeap testHeap = new MinFibonaciHeap(createTestData(1000));

        testHeap.add(-1);
        compHeap.add(-1);
        assertEquals((int) compHeap.peek(), testHeap.peek());
        testHeap.add(-1);
        compHeap.add(-1);

        testHeap.add(-1);
        compHeap.add(-1);

        assertEquals((int) compHeap.peek(), testHeap.peek());

    }

    @Test
    public void MinHeapKeepsItOrderAfterPop() {

        ArrayList<Integer> data = createTestData(1000);

        PriorityQueue<Integer> compHeap = new PriorityQueue<>(data);

        MinFibonaciHeap testHeap = new MinFibonaciHeap(data);

        for (int i = 0; i < data.size()-2; i++) {
            int comp = compHeap.poll();
            int test = testHeap.pop();

            assertEquals(comp, test);

        }

    }

    private ArrayList<Integer> createTestData(int size) {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            values.add(new Random().nextInt(Integer.MAX_VALUE));
        }

        return values;
    }

}
