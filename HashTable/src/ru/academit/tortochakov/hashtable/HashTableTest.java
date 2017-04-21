package ru.academit.tortochakov.hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashTableTest {
    private HashTable<Integer> hTable;

    @BeforeEach
    void setUp() {
        hTable = new HashTable<>(2);
        hTable.add(5);
        hTable.add(7);
        hTable.add(3);
    }

    @Test
    void toArray() throws Exception {
        Object[] arr =  hTable.toArray();
        Arrays.sort(arr);
        assertTrue(Arrays.equals(arr, new Integer[] {3, 5, 7}));
    }

    @Test
    void add() throws Exception {
        assertTrue(hTable.add(10));
        assertTrue(hTable.add(12));
        Object[] arr =  hTable.toArray();
        Arrays.sort(arr);
        assertTrue(Arrays.equals(arr, new Integer[] {3, 5, 7, 10, 12}));
        assertEquals(hTable.size(), 5);
    }

    @Test
    void remove() throws Exception {
        assertTrue(hTable.remove(5));
        Object[] arr =  hTable.toArray();
        Arrays.sort(arr);
        assertTrue(Arrays.equals(arr, new Integer[] {3, 7}));
        assertEquals(hTable.size(), 2);
    }

    @Test
    void remove2() throws Exception {
        assertFalse(hTable.remove(10));
        Object[] arr =  hTable.toArray();
        Arrays.sort(arr);
        assertTrue(Arrays.equals(arr, new Integer[] {3, 5, 7}));
        assertEquals(hTable.size(), 3);
    }

    @Test
    void containsAll() throws Exception {
        assertTrue(hTable.containsAll(new ArrayList(Arrays.asList(3, 7))));
        assertFalse(hTable.containsAll(new ArrayList(Arrays.asList(10, 7))));
        assertFalse(hTable.containsAll(new ArrayList(Arrays.asList(10, 15))));
    }

    @Test
    void contains () throws Exception {
        assertTrue(hTable.contains(3));
        assertFalse(hTable.contains(10));
    }

    @Test
    void addAll() throws Exception {
        assertTrue(hTable.addAll(new ArrayList(Arrays.asList(10, 7))));
        Object[] arr =  hTable.toArray();
        Arrays.sort(arr);
        assertTrue(Arrays.equals(arr, new Integer[] {3, 5, 7, 7, 10}));
        assertEquals(hTable.size(), 5);
    }

    @Test
    void retainAll() throws Exception {
        assertFalse(hTable.retainAll(new ArrayList(Arrays.asList(3, 7, 5))));
        assertEquals(hTable.size(), 3);
        assertTrue(hTable.retainAll(new ArrayList(Arrays.asList(2, 4, 10))));
        assertEquals(hTable.size(), 0);
    }

    @Test
    void removeAll() throws Exception {
        assertFalse(hTable.removeAll(new ArrayList(Arrays.asList(2, 4, 10))));
        assertEquals(hTable.size(), 3);
        assertTrue(hTable.removeAll(new ArrayList(Arrays.asList(3, 7))));
        assertEquals(hTable.size(), 1);
    }

    @Test
    void toArray2() throws Exception {
        Integer[] array = {1, 2, 8};
        Integer[] arr2 = hTable.toArray(array);
        assertEquals(array,arr2);
    }

    @Test
    void toArray3 () throws Exception {
        Integer[] array = {};
        Integer[] arr2 = hTable.toArray(array);
        Arrays.sort(arr2);
        assertTrue(Arrays.equals(arr2, new Integer[] {3, 5, 7}));
    }

    @Test
    void toArray4 () throws Exception {
        Integer[] arr3 = {30, 40, 50, 60, 70};
        Integer[] array2 = hTable.toArray(arr3);
        assertEquals(arr3, array2);
        assertEquals(arr3[3],null);
        arr3[3] = 60;
        Arrays.sort(arr3);
        assertTrue(Arrays.equals(arr3, new Integer[] {3, 5, 7, 60, 70}));
    }

    @Test
    void isEmpty () throws Exception {
        assertFalse(hTable.isEmpty());
        hTable.remove(3);
        hTable.remove(5);
        hTable.remove(7);
        assertTrue(hTable.isEmpty());
    }

    @Test
    void clear () {
        hTable.clear();
        assertEquals(hTable.size(), 0);
    }
}