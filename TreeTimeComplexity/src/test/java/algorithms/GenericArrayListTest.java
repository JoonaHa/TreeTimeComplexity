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

import utils.GenericArrayList;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author JoonaHa
 */
public class GenericArrayListTest {

    private GenericArrayList<Integer> gen;
    private ArrayList<Integer> comp;
    private ArrayList<Integer> data;

    public GenericArrayListTest() {
    }

    @Before
    public void initialize() {
        comp = new ArrayList();
        gen = new GenericArrayList<>();

        data = createTestData(1000);

        for (int i = 0; i < data.size(); i++) {
            gen.add(data.get(i));
            comp.add(data.get(i));
        }

    }

    /**
     * Test of get method, of class GenericArrayList.
     */
    @Test
    public void testGet() {

        for (int i = 0; i < data.size(); i++) {
            assertEquals(comp.get(i), gen.get(i));
        }

    }

    /**
     * Test of set method, of class GenericArrayList.
     */
    @Test
    public void testSet() {

        for (int i = 0; i < data.size(); i++) {
            gen.set(i, 1);
            comp.set(i, 1);
        }

        for (int i = 0; i < data.size(); i++) {
            assertEquals(comp.get(i), gen.get(i));
        }

    }

    /**
     * Test of remove method, of class GenericArrayList.
     */
    @Test
    public void testRemove_GenericType() {

        for (int i = 0; i < comp.size(); i++) {
            int random = new Random().nextInt(comp.size());
            int value = this.comp.get(random);
            comp.remove(new Integer(value));
            gen.remove(new Integer(value));
            assertEquals(comp.get(i), gen.get(i));
        }
    }

    /**
     * Test of remove method, of class GenericArrayList.
     */
    @Test
    public void testRemove_int() {
        for (int i = 0; i < comp.size(); i++) {
            comp.remove(i);
            gen.remove(i);
            assertEquals(comp.get(i), gen.get(i));
        }
    }

    /**
     * Test of size method, of class GenericArrayList.
     */
    @Test
    public void testSize() {
        for (int i = 0; i < comp.size(); i++) {
            comp.remove(i);
            gen.remove(i);
            assertEquals(comp.size(), gen.size());
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
