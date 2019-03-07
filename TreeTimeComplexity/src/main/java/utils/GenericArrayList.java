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

import java.util.ArrayList;

/**
 *
 * @author JoonaHa
 */
public class GenericArrayList<T> {

    private int size;
    private final static int DEAFAULT_SIZE = 50;
    private T[] entries;

    public GenericArrayList() {
        this.entries = (T[]) new Object[DEAFAULT_SIZE];
        this.size = 0;
    }

    public GenericArrayList(ArrayList<T> arraylist) {
        this.entries = (T[]) new Object[arraylist.size() * 3 / 2 + 1];
        this.size = 0;
        for (int i = 0; i < arraylist.size(); i++) {
            this.add(arraylist.get(i));

        }
    }

    public void add(T object) {
        if (size == this.entries.length) {
            widen();
        }

        this.entries[size] = object;
        this.size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index was " + index + "and arraylist's lenght is " + size);
        }

        return this.entries[index];
    }

    public void set(int index, T object) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index was " + index + "and arraylist's lenght is " + size);
        }
        this.entries[index] = object;
    }

    public void remove(T value) {
        int index = findIndex(value);
        if (index < 0) {
            return;
        }

        for (int i = index; i < this.entries.length - 1; i++) {
            this.entries[i] = this.entries[i + 1];
        }

        this.size--;

    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index was " + index + "and arraylist's lenght is " + size);
        }

        for (int i = index; i < this.entries.length - 1; i++) {
            this.entries[i] = this.entries[i + 1];
        }

        this.size--;

    }

    public int size() {
        return size;
    }

    private int findIndex(T value) {
        for (int i = 0; i < this.entries.length; i++) {
            if (value == this.entries[i] || this.entries[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    private void widen() {
        T[] newArray = (T[]) new Object[this.entries.length * 3 / 2 + 1];
        System.arraycopy(this.entries, 0, newArray, 0, this.entries.length);

        this.entries = newArray;
    }

}
