package ru.academit.tortochakov.sort;

import com.sun.javafx.collections.ArrayListenerHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


public class Sort {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Недостаточно аргументов");
            help();
            return;
        } else if (!args[2].equals("-i") && !args[2].equals("-s")) {
            System.out.println("Неизвестный аргумент: " + args[2]);
            help();
            return;
        } else if (!args[3].equals("-a") && !args[3].equals("-d")) {
            System.out.println("Неизвестный аргумент: " + args[3]);
            help();
            return;
        }
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])
        ) {
            ArrayList<Integer> digits = new ArrayList<>(100);
            while (scanner.hasNextInt()) {
                digits.add(scanner.nextInt());
            }

            ArrayList<String> lines = new ArrayList<>(100);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            ArrayList<Integer> sortedNumbers = sortDigits(digits);
            ArrayList<String> sortedLines = sortLines(lines);

            if (args[2].equals("-s")) {
                for (int i = 0; i < sortedLines.size(); i++) {
                    writer.println(sortedLines.get(i));
                }
            } else if (args[3].equals("-a")) {
                for (int i = 0; i < sortedNumbers.size(); i++) {
                    writer.println(sortedNumbers.get(i));
                }
            } else if (args[3].equals("-d")) {
                for (int i = sortedNumbers.size() - 1; i >= 0; i--) {
                    writer.println(sortedNumbers.get(i));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static ArrayList<Integer> sortDigits(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            while (j > 0 && list.get(i) < list.get(j - 1)) {
                j--;
            }
            int temp = list.get(i);
            int n = 0;
            for (int k = i; k > j; k--) {
                list.set(i - n, list.get(k - 1));
                n++;
            }
            list.set(j, temp);
        }
        return list;
    }

    public static ArrayList<String> sortLines(ArrayList<String> list) {
        Collections.sort(list, (o1, o2) -> o1.compareTo(o2));
        return list;
    }

    public static void help() {
        System.out.println("Usage: <input filename>/n" +
                "<output filename>/n" +
                "<-i -a> integer ascending sorting/n" +
                "<-i -d> integer descending sorting/n" +
                "<-s -a> strings ascending sorting");
    }
}

//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -d
//    descending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -a
//    ascending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -s -a
//    strings ascending sorting