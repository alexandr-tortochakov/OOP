package ru.academit.tortochakov.shapes;

public class NewRange {
    public static void main(String[] args) {
        Range firstRange = new Range(4,36);
        Range secondRange = new Range(6,20);

        System.out.println(firstRange.length());
        System.out.println(secondRange.isInside(-2));
    }

    static class Range {
        private double from;
        private double to;

        public Range (double a, double b) {
            this.from = a;
            this.to = b;
            if (a < b) {
                this.from = b;
                this.to = a;
            }
        }

        public double length () {
            return Math.abs(to - from);
        }

        public boolean isInside (double userNumber) {
            return userNumber > from && userNumber < to;
        }

    }
}
