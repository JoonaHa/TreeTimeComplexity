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

/**
 *
 * @author JoonaHa
 */
public class IntCountingSort {

    public static int[] quickSortDescen(int[] input, int start, int end) {

        int partitionPoint = partition(input, start, end);

        if (partitionPoint - 1 > start) {
            return quickSortDescen(input, start, partitionPoint - 1);
        }
        if (partitionPoint + 1 < end) {
            quickSortDescen(input, partitionPoint + 1, end);
        }

        return input;
    }

    private static int partition(int[] array, int start, int end) {
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

    public static int[] quickSortAscend(int[] input, int start, int end) {

        int partitionPoint = partition2(input, start, end);

        if (partitionPoint - 1 > start) {
            return quickSortAscend(input, start, partitionPoint - 1);
        }
        if (partitionPoint + 1 < end) {
            quickSortAscend(input, partitionPoint + 1, end);
        }

        return input;
    }

    private static int partition2(int[] array, int start, int end) {
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
