package ru.academit.tortochakov.sort;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Sort {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Недостаточно аргументов");
            help();
            return;
        } else if (!args[2].equals("-i")) {
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
            ArrayList<Integer> digits = new ArrayList<>();
            while (scanner.hasNextInt()) {
                digits.add(scanner.nextInt());
            }
            int[] numbersArray = new int[digits.size()];
            for (int i = 0; i < digits.size(); i++) {
                numbersArray[i] = digits.get(i);
            }
            int[] sortedArray = sort(numbersArray);
            if (args[3].equals("-a")) {
                for (int i = 0; i < sortedArray.length; i++) {
                    writer.println(Integer.toString(sortedArray[i]));
                }
            } else if (args[3].equals("-d")) {
                for (int i = sortedArray.length - 1; i >= 0; i--) {
                    writer.println(Integer.toString(sortedArray[i]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

    public static int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[i] < array[j - 1]) {
                j--;
            }
            int temp = array[i];
            for (int k = i; k > j; k--) {
                array[k] = array[k - 1];
            }
            array[j] = temp;
        }
        return array;
    }

    public static void help() {
        System.out.println("Usage: <input filename> <output filename> -i -[a|d]");
    }
}

//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -d
//    descending sorting
//    java -cp <path to sort.class directory location> Sort <path to input filename> <path to output filename> -i -a
//    ascending sorting