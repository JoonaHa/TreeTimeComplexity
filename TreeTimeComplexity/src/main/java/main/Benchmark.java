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
package main;

import algorithms.Heaps;
import java.util.stream.LongStream;

/**
 *
 * @author JoonaHa
 */
public class Benchmark {

    private Heaps heap;
    private int[] input;
    private int iterations;

    public Benchmark(Heaps heap, int iterations, int inputLenght) {
        this.heap = heap;
        this.input = createTestData(inputLenght);
        this.iterations = iterations;
    }

    public long[] testAdd() {
        long[] times = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            times[i] = runAdd();
        }

        long[] results = new long[3];

        results[0] = findMin(times);
        results[1] = findMax(times);
        results[2] = LongStream.of(times).sum() / iterations;

        return results;

    }

    public long[] testPeek() {
        long[] times = new long[iterations];
        for (int i = 0; i < iterations; i++) {
            times[i] = runPeek();
        }

        long[] results = new long[3];

        results[0] = findMin(times);
        results[1] = findMax(times);
        results[2] = LongStream.of(times).sum() / iterations;

        return results;

    }

    public long[] testPop() {
        long[] times = new long[iterations];
        for (int i = 0; i < iterations; i++) {

            times[i] = runPop();
        }

        long[] results = new long[3];

        results[0] = findMin(times);
        results[1] = findMax(times);
        results[2] = LongStream.of(times).sum() / iterations;

        return results;

    }

    public long[] testDecreaseKey() {
        long[] times = new long[iterations];
        for (int i = 0; i < iterations; i++) {

            times[i] = runDecreaseKey();
        }

        long[] results = new long[3];

        results[0] = findMin(times);
        results[1] = findMax(times);
        results[2] = LongStream.of(times).sum() / iterations;

        return results;

    }

    public long[] testDelete() {
        long[] times = new long[iterations];
        for (int i = 0; i < iterations; i++) {

            times[i] = runDelete();
        }

        long[] results = new long[3];

        results[0] = findMin(times);
        results[1] = findMax(times);
        results[2] = LongStream.of(times).sum() / iterations;

        return results;

    }

    private long runAdd() {
        heap.clear();
        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.add(input[i]);
        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runPop() {

        if (heap.getSize() != input.length) {
            heap.clear();
            for (int i = 0; i < input.length; i++) {
                heap.add(input[i]);
            }
        }
        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.pop();

        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runPeek() {

        if (heap.getSize() != input.length) {
            heap.clear();
            for (int i = 0; i < input.length; i++) {
                heap.add(input[i]);
            }
        }
        long start = System.nanoTime();

        heap.peek();

        long end = System.nanoTime();

        return end - start;
    }

    private long runDecreaseKey() {

        heap.clear();
        for (int i = 0; i < input.length; i++) {
            heap.add(input[i]);
        }

        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.decreaseKey(input[i]);

        }

        long end = System.nanoTime();

        return end - start;

    }

    private long runDelete() {

        if (heap.getSize() == 0) {
            for (int i = 0; i < input.length; i++) {
                heap.add(input[i]);
            }
        }
        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.delete(input[i]);

        }

        long end = System.nanoTime();

        heap.clear();

        return end - start;

    }

    private long findMin(long[] array) {
        long min = array[0];
        for (int i = 0; i < array.length; i++) {

            if (array[i] < min) {
                min = array[i];
            }

        }

        return min;
    }

    private long findMax(long[] array) {
        long max = array[0];
        for (int i = 0; i < array.length; i++) {

            if (array[i] > max) {
                max = array[i];
            }

        }

        return max;
    }

    //Generate pseudorandom testdata from lasta digits of system.nanotime()
    private int[] createTestData(int size) {

        int[] values = new int[size];

        for (int i = 0; i < size; i++) {

            values[i] = ((int) ((System.nanoTime() % 10000 * 0.0001) * Integer.MAX_VALUE));
            System.out.println(values[i]);
            System.out.println((System.nanoTime() % 10000 * 0.0001));
        }

        return values;
    }

}
