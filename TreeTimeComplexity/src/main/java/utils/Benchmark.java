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
package utils;

import algorithms.MinHeaps;
import java.util.stream.LongStream;

/**
 * Class for testing Objects that inherit MinHeaps
 *
 * @author JoonaHa
 */
public class Benchmark {

    private MinHeaps heap;
    private int[] input;
    private int iterations;
    private InputTypes sorting;

    /**
     * *
     *
     *
     * @param heap Object to benchmark
     * @param iterations Number of iterations the benchmark will run. The
     * average running time calculation is based on the number of iterations.
     * @param inputLenght Lenght of randomised input data which will be used for
     * testing.
     * @param sorting Is the input data random or in ascending or descending
     * order.
     */
    public Benchmark(MinHeaps heap, int iterations, int inputLenght, InputTypes sorting) {
        this.heap = heap;
        this.iterations = iterations;
        this.sorting = sorting;
        this.input = createTestData(inputLenght);
    }

    // Public methods for benchmarking specific operations.
    /**
     * Benchmarks add operation. Results in long[3] array.
     *
     * @return returns bechmark times int a long[3] array. long[0] is min-value,
     * long[1] max and long[2] is average.
     */
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

    /**
     * Benchmarks peek operation. Results in long[3] array.
     *
     * @return returns bechmark times int a long[3] array. long[0] is min-value,
     * long[1] max and long[2] is average.
     */
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

    /**
     * Benchmarks pop operation. Results in long[3] array.
     *
     * @return returns bechmark times int a long[3] array. long[0] is min-value,
     * long[1] max and long[2] is average.
     */
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

    /**
     * Benchmarks pop operation. Results in long[3] array.
     *
     * @return returns bechmark times int a long[3] array. long[0] is min-value,
     * long[1] max and long[2] is average.
     */
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

    /**
     * Benchmarks delete operation. Results in long[3] array.
     *
     * @return returns bechmark times int a long[3] array. long[0] is min-value,
     * long[1] max and long[2] is average.
     */
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

    //methods for running one iteration of a test
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

        heap.clear();
        for (int i = 0; i < input.length; i++) {
            heap.add(input[i]);
        }
        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.pop();

        }

        long end = System.nanoTime();

        return end - start;
    }

    private long runPeek() {

        heap.clear();
        for (int i = 0; i < input.length; i++) {
            heap.add(input[i]);
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

        heap.clear();
        for (int i = 0; i < input.length; i++) {
            heap.add(input[i]);
        }

        long start = System.nanoTime();

        for (int i = 0; i < input.length; i++) {
            heap.delete(input[i]);

        }

        long end = System.nanoTime();

        return end - start;

    }

    //find min value
    private long findMin(long[] array) {
        long min = array[0];
        for (int i = 0; i < array.length; i++) {

            if (array[i] < min) {
                min = array[i];
            }

        }

        return min;
    }

    //find max value
    private long findMax(long[] array) {
        long max = array[0];
        for (int i = 0; i < array.length; i++) {

            if (array[i] > max) {
                max = array[i];
            }

        }

        return max;
    }

    //Generate pseudorandom testdata from last digits of system.nanotime()
    private int[] createTestData(int size) {

        int[] values = new int[size];

        for (int i = 0; i < size; i++) {

            values[i] = ((int) ((System.nanoTime() % 10000 * 0.0001) * Integer.MAX_VALUE));
        }

        //sort generated  data based on the instance varibale this.sorting
        if (this.sorting.equals(InputTypes.ASCENDING)) {
            values = IntQuickSort.quickSortAscend(values, 0, size - 1);
        }
        if (this.sorting.equals(InputTypes.DESCENDING)) {
            values = IntQuickSort.quickSortDescen(values, 0, size - 1);

        }

        return values;
    }

}
