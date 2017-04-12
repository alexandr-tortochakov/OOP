package ru.academit.tortochakov.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    private LinkedList<Integer> linkedList;
    private LinkedList<Integer> linkedListEmpty;
    private LinkedList<Integer> linkedListDoubles;
    private LinkedList<Integer> linkedListOne;
    private LinkedList<Integer> linkedListTwo;


    @BeforeEach
    void setUp() {
        linkedList = new LinkedList<>(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 1, 4)));
        linkedListDoubles = new LinkedList<>(new ArrayList<>(Arrays.asList(2, 2, 3, 1, 3, 5, 5)));
        linkedListEmpty = new LinkedList<>(new ArrayList<>(Collections.emptyList()));
        linkedListOne = new LinkedList<>(new ArrayList<>(Arrays.asList(3)));
        linkedListTwo = new LinkedList<>(new ArrayList<>(Arrays.asList(3, 4)));
    }

    @Test
    void testAddFirst() throws Exception {
        linkedList.addFirst(4);
        assertTrue(Arrays.equals(linkedList.toArray(), new Integer[]{4, 1, 2, 3, 2, 1, 4}));
    }

    @Test
    void testAddLast() throws Exception {
        linkedList.addLast(4);
        assertTrue(Arrays.equals(linkedList.toArray(), new Integer[]{1, 2, 3, 2, 1, 4, 4}));
    }

    @Test
    void testToString() throws Exception {
        assertTrue(linkedList.toString().equals("[1, 2, 3, 2, 1, 4]"));
    }

    @Test
    void testToStringEmpty() throws Exception {
        assertTrue(linkedListEmpty.toString().equals("[]"));
    }

    @Test
    void testRemoveFirst() throws Exception {
        int data = linkedList.removeFirst();
        assertTrue(Arrays.equals(linkedList.toArray(), new Integer[]{2, 3, 2, 1, 4}));
        assertEquals(data, 1);
    }

    @Test
    void testRemoveFirstEmpty() throws Exception {
        assertTrue(linkedListEmpty.removeFirst() == null);
    }

    @Test
    void testRemoveLast() throws Exception {
        int dada = linkedList.removeLast();
        assertTrue(Arrays.equals(linkedList.toArray(), new Integer[]{1, 2, 3, 2, 1}));
        assertEquals(dada, 4);
    }

    @Test
    void testRemoveLastEmpty() throws Exception {
        assertTrue(linkedListEmpty.removeLast() == null);
    }

    @Test
    void testGetFirst() throws Exception {
        assertEquals(linkedList.getFirst(), (Integer) 1);
    }

    @Test
    void testGetFirstEmpty() throws Exception {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            linkedListEmpty.getFirst();
        });
        assertEquals("Список пустой", exception.getMessage());
    }

    @Test
    void testGetLast() throws Exception {
        assertEquals(linkedList.getLast(), (Integer) 4);
    }

    @Test
    void testGetLastEmpty() throws Exception {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            linkedListEmpty.getLast();
        });
        assertEquals("Список пустой", exception.getMessage());
    }

    @Test
    void testRemoveFirstOccurrence() throws Exception {
        assertFalse(linkedListDoubles.removeFirstOccurrence(-5));
        assertTrue(Arrays.equals(linkedListDoubles.toArray(), new Integer[]{2, 2, 3, 1, 3, 5, 5}));
    }

    @Test
    void testRemoveFirstOccurrence2() throws Exception {
        assertTrue(linkedListDoubles.removeFirstOccurrence(3));
        assertTrue(Arrays.equals(linkedListDoubles.toArray(), new Integer[]{2, 2, 1, 3, 5, 5}));
    }

    @Test
    void testRemoveFirstOccurrenceEmpty() throws Exception {
        assertFalse(linkedListEmpty.removeFirstOccurrence(3));
        assertTrue(Arrays.equals(linkedListEmpty.toArray(), new Integer[]{}));
    }

    @Test
    void testRemoveLastOccurrence() throws Exception {
        assertFalse(linkedListDoubles.removeLastOccurrence(-5));
        assertTrue(Arrays.equals(linkedListDoubles.toArray(), new Integer[]{2, 2, 3, 1, 3, 5, 5}));
    }

    @Test
    void testRemoveLastOccurrence2() throws Exception {
        assertTrue(linkedListDoubles.removeLastOccurrence(2));
        assertTrue(Arrays.equals(linkedListDoubles.toArray(), new Integer[]{2, 3, 1, 3, 5, 5}));
    }
    @Test
    void testRemoveLastOcc() throws Exception{
        assertTrue(linkedListOne.removeLastOccurrence(3));
        assertTrue(Arrays.equals(linkedListOne.toArray(), new Integer[]{}));

    }

    @Test
    void testRemoveLastOccurrenceEmpty() throws Exception {
        assertFalse(linkedListEmpty.removeLastOccurrence(3));
        assertTrue(Arrays.equals(linkedListEmpty.toArray(), new Integer[]{}));
    }

    @Test
    void testLastIndexOf() throws Exception {
        assertEquals(linkedListDoubles.lastIndexOf(-5), -1);
    }
}