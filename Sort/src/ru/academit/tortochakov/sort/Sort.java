package ru.academit.tortochakov.sort;

import com.sun.javafx.collections.ArrayListenerHelper;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

            ArrayList<String> lines = new ArrayList<>(100);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }

            ArrayList<Integer> digits = new ArrayList<>(100);
            if (args[2].equals("-i")) {
                for (String line : lines) {
                    digits.add(Integer.parseInt(line));
                }
            }

            if (args[2].equals("-s")) {
                ArrayList<String> sortedLines = sortLines(lines);
                if (args[2].equals("-d")) {
                    Collections.reverse(sortedLines);
                }
                for (String sortedLine : sortedLines) {
                    writer.println(sortedLine);
                }
            } else {
                ArrayList<Integer> sortedNumbers = sortDigits(digits);
                if (args[3].equals("-d")) {
                    Collections.reverse(sortedNumbers);
                }
                for (Integer sortedNumber : sortedNumbers) {
                    writer.println(sortedNumber);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
//        catch (IOException t) {
//            System.out.println("Закончилось место на диске");
//        }
    }

    public static ArrayList<Integer> sortDigits(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            int temp = list.get(i);
            while (j > 0 && temp < list.get(j - 1)) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, temp);
        }
        return list;
    }

    public static ArrayList<String> sortLines(ArrayList<String> list) {
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            String temp = list.get(i);
            while (j > 0 && temp.compareTo(list.get(j - 1)) < 0) {
                list.set(j, list.get(j - 1));
                j--;
            }
            list.set(j, temp);
        }
        return list;
    }

    public static void help() {
        System.out.println("Usage: <input filename>/n" +
                "<output filename>/n" +
                "<-i -a> integer ascending sorting/n" +
                "<-i -d> integer descending sorting/n" +
                "<-s -a> strings ascending sorting/n" +
                "<-s -d> strings descending sorting");
    }
}
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -a
//    ascending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -d
//    descending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -s -a
//    strings ascending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -s -d
//    strings descending sorting