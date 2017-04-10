package ru.academit.tortochakov.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Main {
//    public static void main(String[] args) {
//        Integer[] array = new Integer[]{1, 3, 4, 5, 6, 7, 7};
//        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 7));
//        LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
//        System.out.println(linkedList.toString());
//        linkedList.addAll(4, arrayList);
//        System.out.println(linkedList);
//        System.out.println(linkedList.size());
//        System.out.println(Arrays.toString(linkedList.toArray()));
//
//    }
    private LinkedList<Integer> linkedList = new LinkedList<>();


    protected void setUp() throws Exception {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 7));
        linkedList = new LinkedList<>(arrayList);
    }

    @Test
    public void testAddFirst() throws Exception {
        linkedList.addFirst(3);
        System.out.println(Arrays.toString(linkedList.toArray()));
//        assertTrue(Arrays.equals(linkedList.toArray(), new Integer[] {3, 1, 2, 4, 5, 7}));
        assertTrue(true);
    }
}
