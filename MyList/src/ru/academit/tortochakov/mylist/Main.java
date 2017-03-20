package ru.academit.tortochakov.mylist;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        MyList myList = new MyList(3);
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1, 4, 5, 6, 8));

        System.out.println("myList.set(0, 2): ");
        myList.set(0, 2);
        System.out.println(myList + "\n");

        System.out.println("myList.addAll(arrayList): ");
        myList.addAll(arrayList);
        System.out.println(myList + "\n");

        System.out.println("myList.remove(1):");
        myList.remove(1);
        System.out.println(myList + "\n");

        System.out.println("myList.contains(2)");
        System.out.println(myList.contains(2));

        System.out.println("myList.add(3,7):");
        myList.add(3,7);
        System.out.println(myList + "\n");

        System.out.println("myList.add(6):");
        myList.add(6);
        System.out.println(myList + "\n");

        System.out.println("myList.addAll(3, arrayList):");
        myList.addAll(3, arrayList);
        System.out.println(myList + "\n");

        System.out.println("myList.lastIndexOf(2)):");
        System.out.println(myList.lastIndexOf(2));

        System.out.println("myList.subList(2, 4)):");
        System.out.println(myList.subList(2, 4));

        System.out.println("myList.add(0,2), myList.add(13, 7):");
        myList.add(0,2);
        myList.add(13, 7);
        System.out.println(myList + "\n");

        System.out.println("myList.containsAll(arrayList)):");
        System.out.println(myList.containsAll(arrayList));
    }
}
