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
 * Quicksort implementations for intgers
 *
 * @author JoonaHa
 */
public class IntQuickSort {

    /**
     * Recursively quicsort in descending order
     *
     * @param input Array to sort
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSortDescen(int[] input, int start, int end) {

        int partitionPoint = partitionDesc(input, start, end);

        if (partitionPoint - 1 > start) {
            return quickSortDescen(input, start, partitionPoint - 1);
        }
        if (partitionPoint + 1 < end) {
            quickSortDescen(input, partitionPoint + 1, end);
        }

        return input;
    }

    /**
     * Own partion method for descending order
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partitionDesc(int[] array, int start, int end) {
        //Chosen pivot is just the right side of partition
        int pivotPoint = array[end];

        for (int i = start; i < end; i++) {

            if (array[i] < pivotPoint) {

                int temp = array[start];
                array[start] = array[i];
                array[i] = temp;
                start++;
            }
        }

        int temp = array[start];
        array[start] = pivotPoint;
        array[end] = temp;

        return start;

    }

    /**
     * Recursively quicsort in ascending order
     *
     * @param input Array to sort
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSortAscend(int[] input, int start, int end) {

        int partitionPoint = partitionAsc(input, start, end);

        if (partitionPoint - 1 > start) {
            return quickSortAscend(input, start, partitionPoint - 1);
        }
        if (partitionPoint + 1 < end) {
            quickSortAscend(input, partitionPoint + 1, end);
        }

        return input;
    }

    /**
     * Own partion method for ascending order
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    private static int partitionAsc(int[] array, int start, int end) {
        int pivotPoint = array[end];

        for (int i = start; i < end; i++) {

            if (array[i] > pivotPoint) {

                int temp = array[start];
                array[start] = array[i];
                array[i] = temp;
                start++;
            }
        }

        int temp = array[start];
        array[start] = pivotPoint;
        array[end] = temp;

        return start;

    }
}
