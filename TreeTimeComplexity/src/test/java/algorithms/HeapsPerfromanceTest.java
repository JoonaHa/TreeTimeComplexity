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
import java.util.stream.LongStream;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author JoonaHa
 */
public class HeapsPerfromanceTest {

    PriorityQueue<Integer> compHeap;
    ArrayList<MinHeaps> testHeaps;

    ArrayList<Integer> data;
    int iterations;

    @Before
    public void initialize() {

        data = createTestData(1000);
        iterations = 300;
        testHeaps = new ArrayList<>();
        compHeap = new PriorityQueue<>(data);

        testHeaps.add(new MinBinaryHeap(new GenericArrayList<>(data)));
        testHeaps.add(new MinBinomialHeap(new GenericArrayList<>(data)));
        testHeaps.add(new MinFibonaciHeap(new GenericArrayList<>(data)));

    }

    @Test
    public void testAdd() {

        long[] results = new long[4];

        for (int i = 0; i < testHeaps.size(); i++) {
            long[] times = new long[iterations];

            for (int j = 0; j < iterations; j++) {
                times[j] = runAdd(testHeaps.get(i));

            }
            results[i] = LongStream.of(times).sum() / iterations;

        }

        long[] compTimes = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            compTimes[i] = runCompAdd();

        }

        results[3] = LongStream.of(compTimes).sum() / iterations;

        System.out.println("\n--------TEST ADD--------");
        System.out.println("BinaryHeap/PriorityQue: " + results[0] + "/" + results[3]);
        System.out.println("BinomialHeap/PriorityQue: " + results[1] + "/" + results[3]);
        System.out.println("FibonacciHeap/PriorityQue: " + results[2] + "/" + results[3]);

    }

    @Test
    public void testPeek() {
        long[] results = new long[4];

        for (int i = 0; i < testHeaps.size(); i++) {
            long[] times = new long[iterations];

            for (int j = 0; j < iterations; j++) {
                times[j] = runPeek(testHeaps.get(i));

            }
            results[i] = LongStream.of(times).sum() / iterations;

        }

        long[] compTimes = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            compTimes[i] = runCompPeek();

        }

        results[3] = LongStream.of(compTimes).sum() / iterations;

        System.out.println("\n--------TEST PEEK--------");
        System.out.println("BinaryHeap/PriorityQue: " + results[0] + "/" + results[3]);
        System.out.println("BinomialHeap/PriorityQue: " + results[1] + "/" + results[3]);
        System.out.println("FibonacciHeap/PriorityQue: " + results[2] + "/" + results[3]);

    }

    @Test
    public void testPop() {
        long[] results = new long[4];

        for (int i = 0; i < testHeaps.size(); i++) {
            long[] times = new long[iterations];

            for (int j = 0; j < iterations; j++) {
                times[j] = runPop(testHeaps.get(i));

            }
            results[i] = LongStream.of(times).sum() / iterations;

        }

        long[] compTimes = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            compTimes[i] = runCompPop();

        }

        results[3] = LongStream.of(compTimes).sum() / iterations;

        System.out.println("\n--------TEST POP--------");
        System.out.println("BinaryHeap/PriorityQue: " + results[0] + "/" + results[3]);
        System.out.println("BinomialHeap/PriorityQue: " + results[1] + "/" + results[3]);
        System.out.println("FibonacciHeap/PriorityQue: " + results[2] + "/" + results[3]);

    }

    @Test
    public void testDelete() {
        long[] results = new long[4];

        for (int i = 0; i < testHeaps.size(); i++) {
            long[] times = new long[iterations];

            for (int j = 0; j < iterations; j++) {
                times[j] = runDelete(testHeaps.get(i));

            }
            results[i] = LongStream.of(times).sum() / iterations;

        }

        long[] compTimes = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            compTimes[i] = runCompDelete();

        }

        results[3] = LongStream.of(compTimes).sum() / iterations;

        System.out.println("\n--------TEST DELETE--------");
        System.out.println("BinaryHeap/PriorityQue: " + results[0] + "/" + results[3]);
        System.out.println("BinomialHeap/PriorityQue: " + results[1] + "/" + results[3]);
        System.out.println("FibonacciHeap/PriorityQue: " + results[2] + "/" + results[3]);

    }

    @Test
    public void testDecreaseKey() {
        long[] results = new long[4];

        for (int i = 0; i < testHeaps.size(); i++) {
            long[] times = new long[iterations];

            for (int j = 0; j < iterations; j++) {
                times[j] = runDecreaseKey(testHeaps.get(i));

            }
            results[i] = LongStream.of(times).sum() / iterations;

        }

        long[] compTimes = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            compTimes[i] = runCompDecreaseKey();

        }

        results[3] = LongStream.of(compTimes).sum() / iterations;

        System.out.println("\n--------TEST DECREASE KEY--------");
        System.out.println("BinaryHeap/PriorityQue: " + results[0] + "/" + results[3]);
        System.out.println("BinomialHeap/PriorityQue: " + results[1] + "/" + results[3]);
        System.out.println("FibonacciHeap/PriorityQue: " + results[2] + "/" + results[3]);

    }

    private long runAdd(MinHeaps heap) {
        heap.clear();

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            heap.add(data.get(i));
        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runPop(MinHeaps heap) {
        heap.clear();
        for (int i = 0; i < data.size(); i++) {
            heap.add(data.get(i));
        }

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            heap.pop();

        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runPeek(MinHeaps heap) {
        heap.clear();
        for (int i = 0; i < data.size(); i++) {
            heap.add(data.get(i));
        }

        long start = System.nanoTime();

        heap.peek();

        long end = System.nanoTime();

        return end - start;
    }

    private long runDecreaseKey(MinHeaps heap) {
        heap.clear();
        for (int i = 0; i < data.size(); i++) {
            heap.add(data.get(i));
        }

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            heap.decreaseKey(data.get(i));

        }

        long end = System.nanoTime();

        return end - start;

    }

    private long runDelete(MinHeaps heap) {
        heap.clear();
        for (int i = 0; i < data.size(); i++) {
            heap.add(data.get(i));
        }

        long start = System.nanoTime();

        for (int i = 0; i < data.size() - 1; i++) {
            heap.delete(data.get(i));

        }

        long end = System.nanoTime();

        return end - start;

    }

    private long runCompAdd() {
        compHeap.clear();

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            compHeap.add(data.get(i));
        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runCompPop() {
        compHeap.clear();
        for (int i = 0; i < data.size(); i++) {
            compHeap.add(data.get(i));
        }
        compHeap.clear();

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            compHeap.poll();

        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runCompPeek() {
        compHeap.clear();
        for (int i = 0; i < data.size(); i++) {
            compHeap.add(data.get(i));
        }

        long start = System.nanoTime();

        compHeap.peek();

        long end = System.nanoTime();

        return end - start;
    }

    private long runCompDecreaseKey() {
        compHeap.clear();
        for (int i = 0; i < data.size(); i++) {
            compHeap.add(data.get(i));
        }

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            compHeap.remove(data.get(i));
            compHeap.add(data.get(i) - 1);

        }

        long end = System.nanoTime();

        return end - start;

    }

    private long runCompDelete() {
        compHeap.clear();
        for (int i = 0; i < data.size(); i++) {
            compHeap.add(data.get(i));
        }

        long start = System.nanoTime();

        for (int i = 0; i < data.size(); i++) {
            compHeap.remove(data.get(i));

        }

        long end = System.nanoTime();

        return end - start;

    }

    private ArrayList<Integer> createTestData(int size) {

        ArrayList<Integer> values = new ArrayList<>();

        for (int i = 0; i < size; i++) {

            values.add(new Random().nextInt(Integer.MAX_VALUE));
        }

        return values;
    }

}
