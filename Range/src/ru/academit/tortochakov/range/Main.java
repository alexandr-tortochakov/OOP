package ru.academit.tortochakov.range;


public class Main {
    public static void main(String[] args) {
        Range firstRange = new Range(5, 8);
        Range secondRange = new Range(6, 16);

        System.out.println("Длина интервала: ");
        System.out.println(firstRange.length());

        System.out.println("Проверка принадлежности: ");
        if (firstRange.isInside(-2)) {
            System.out.println("Принадлежит интервалу");
        } else {
            System.out.println("Не принадлежит интервалу");
        }

        System.out.println("Пересечение: ");
        Range intersectionRange = firstRange.intersection(secondRange);
        if (intersectionRange != null) {
            System.out.println(intersectionRange.toString());
        } else {
            System.out.println("Интервалы не пересекаются");
        }

        System.out.println("Объединение: ");
        Range[] mergeRanges = firstRange.merge(secondRange);
        for (int i = 0; i < mergeRanges.length; i++) {
            System.out.println(mergeRanges[i].toString());
        }

        System.out.println("Разность: ");
        Range[] subtractionRanges = firstRange.subtraction(secondRange);
        if (subtractionRanges != null) {
            for (int i = 0; i < subtractionRanges.length; i++) {
                System.out.println(subtractionRanges[i].toString());
            }
        } else {
            System.out.println("Разность равна пустому интервалу");
        }
    }
}